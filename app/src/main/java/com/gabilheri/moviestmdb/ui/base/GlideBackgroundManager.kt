package com.gabilheri.moviestmdb.ui.base

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.support.v17.leanback.app.BackgroundManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import java.lang.ref.WeakReference
import java.util.*

/**
 * @author Marcus Gabilheri (gabilher)
 * @since 7/21/16
 */
/**
 * NOTE: >> DO NOT USE << images with transparency on then. The BackgroundManager freaks out and a really weird
 * stuff happens with the cards.
 */
class GlideBackgroundManager(activity: Activity?) {
    private val mActivityWeakReference: WeakReference<Activity?>
    private val mBackgroundManager: BackgroundManager?
    private val mHandler = Handler(Looper.getMainLooper())
    private var mBackgroundURI: String? = null
    private var mBackgroundTimer: Timer? = null
    private val mGlideDrawableSimpleTarget: SimpleTarget<GlideDrawable> = object : SimpleTarget<GlideDrawable>() {
        override fun onResourceReady(resource: GlideDrawable, glideAnimation: GlideAnimation<in GlideDrawable>) {
            setBackground(resource)
        }
    }

    fun loadImage(imageUrl: String?) {
        mBackgroundURI = imageUrl
        startBackgroundTimer()
    }

    fun setBackground(drawable: Drawable?) {
        if (mBackgroundManager != null) {
            if (!mBackgroundManager.isAttached) {
                mBackgroundManager.attach(mActivityWeakReference.get()!!.window)
            }
            mBackgroundManager.drawable = drawable
        }
    }

    private inner class UpdateBackgroundTask : TimerTask() {
        override fun run() {
            mHandler.post {
                if (mBackgroundURI != null) {
                    updateBackground()
                }
            }
        }
    }

    /**
     * Cancels an ongoing background change
     */
    fun cancelBackgroundChange() {
        mBackgroundURI = null
        cancelTimer()
    }

    /**
     * Stops the timer
     */
    private fun cancelTimer() {
        if (mBackgroundTimer != null) {
            mBackgroundTimer!!.cancel()
        }
    }

    /**
     * Starts the background change timer
     */
    private fun startBackgroundTimer() {
        cancelTimer()
        mBackgroundTimer = Timer()
        /* set delay time to reduce too much background image loading process */mBackgroundTimer!!.schedule(UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY.toLong())
    }

    /**
     * Updates the background with the last known URI
     */
    fun updateBackground() {
        if (mActivityWeakReference.get() != null) {
            Glide.with(mActivityWeakReference.get())
                    .load(mBackgroundURI)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mGlideDrawableSimpleTarget)
        }
    }

    companion object {
        private val TAG = GlideBackgroundManager::class.java.simpleName
        private const val BACKGROUND_UPDATE_DELAY = 200
        var instance: GlideBackgroundManager? = null
    }

    /**
     * @param activity
     * The activity to which this WindowManager is attached
     */
    init {
        mActivityWeakReference = WeakReference(activity)
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(activity!!.window)
    }
}