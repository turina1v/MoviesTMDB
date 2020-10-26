package com.gabilheri.moviestmdb.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.leanback.widget.BaseCardView

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/8/16.
 */
abstract class BindableCardView<T> : BaseCardView {
    constructor(context: Context?) : super(context) {
        initLayout()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initLayout()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initLayout()
    }

    private fun initLayout() {
        isFocusable = true
        isFocusableInTouchMode = true
        val inflater = LayoutInflater.from(context)
        inflater.inflate(layoutResource, this)
    }

    protected abstract fun bind(data: T)

    @get:LayoutRes
    protected abstract val layoutResource: Int
}