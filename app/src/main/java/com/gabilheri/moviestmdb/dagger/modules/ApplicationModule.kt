package com.gabilheri.moviestmdb.dagger.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 9/4/16.
 */
@Module
class ApplicationModule(var mApplication: Application) {
    @Singleton
    @Provides
    fun providesApplication(): Application {
        return mApplication
    }

}