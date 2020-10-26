package com.gabilheri.moviestmdb.dagger.components

import com.gabilheri.moviestmdb.App
import com.gabilheri.moviestmdb.dagger.AppScope
import com.gabilheri.moviestmdb.dagger.modules.ApplicationModule
import com.gabilheri.moviestmdb.dagger.modules.HttpClientModule
import com.gabilheri.moviestmdb.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 9/4/16.
 */
@AppScope
@Singleton
@Component(modules = [ApplicationModule::class, HttpClientModule::class])
interface ApplicationComponent {
    fun inject(app: App?)
    fun inject(mainFragment: MainFragment?)
}