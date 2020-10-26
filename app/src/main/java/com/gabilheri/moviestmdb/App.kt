package com.gabilheri.moviestmdb

import android.app.Application
import com.gabilheri.moviestmdb.dagger.components.ApplicationComponent
import com.gabilheri.moviestmdb.dagger.modules.ApplicationModule
import com.gabilheri.moviestmdb.dagger.modules.HttpClientModule
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/11/16.
 */
class App : Application() {
    private var mApplicationComponent: ApplicationComponent? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        // Creates Dagger Graph
        /*mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .httpClientModule(HttpClientModule())
                .build()
        mApplicationComponent!!.inject(this)*/
    }

    fun appComponent(): ApplicationComponent? {
        return mApplicationComponent
    }

    companion object {
        private var instance: App? = null
        fun instance(): App? {
            return instance
        }
    }
}