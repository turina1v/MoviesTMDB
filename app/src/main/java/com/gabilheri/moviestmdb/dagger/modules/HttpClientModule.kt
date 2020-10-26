package com.gabilheri.moviestmdb.dagger.modules

import android.app.Application
import com.gabilheri.moviestmdb.dagger.AppScope
import com.gabilheri.moviestmdb.data.Api.TheMovieDbAPI
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 9/4/16.
 */
@Module
class HttpClientModule {
    @Provides
    @AppScope
    fun provideOkHttpClient(app: Application): OkHttpClient {
        val cacheDir = File(app.cacheDir, "http")
        return OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .cache(Cache(cacheDir, DISK_CACHE_SIZE))
                .build()
    }

    @Provides
    @Named("TVDB") // Name is used in case a second Retrofit api is provided.
    @AppScope
    fun provideTVDBRestAdapter(moshiConverterFactory: MoshiConverterFactory?, okHttpClient: OkHttpClient): Retrofit {
        var okHttpClient = okHttpClient
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(interceptor)
                .build()
        return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(moshiConverterFactory)
                .build()
    }

    @Provides
    fun provideFithubApi(@Named("TVDB") restAdapter: Retrofit): TheMovieDbAPI {
        return restAdapter.create(TheMovieDbAPI::class.java)
    }

    @Provides
    @AppScope
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    companion object {
        private const val DISK_CACHE_SIZE = 50 * 1024 * 1024 // 50MB
                .toLong()
        const val BACKDROP_URL = "http://image.tmdb.org/t/p/w1280"
        const val POSTER_URL = "http://image.tmdb.org/t/p/w500"
        const val API_URL = "https://api.themoviedb.org/3/"
        const val NOW_PLAYING = "movie/now_playing"
        const val LATEST = "movie/latest"
        const val POPULAR = "movie/popular"
        const val TOP_RATED = "movie/top_rated"
        const val UPCOMING = "movie/upcoming"
        const val MOVIE = "movie/"
        const val PERSON = "person/"
        const val DISCOVER = "discover/movie/"
        const val SEARCH_MOVIE = "search/movie/"
        const val TV = "tv/"
    }
}