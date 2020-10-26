package com.gabilheri.moviestmdb.data.Api

import com.gabilheri.moviestmdb.dagger.modules.HttpClientModule
import com.gabilheri.moviestmdb.data.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/8/16.
 */
interface TheMovieDbAPI {
    @GET(HttpClientModule.Companion.NOW_PLAYING)
    fun getNowPlayingMovies(
            @Query("api_key") apiKey: String?,
            @Query("page") page: Int
    ): Observable<MovieResponse?>

    @GET(HttpClientModule.Companion.TOP_RATED)
    fun getTopRatedMovies(
            @Query("api_key") apiKey: String?,
            @Query("page") page: Int
    ): Observable<MovieResponse?>

    @GET(HttpClientModule.Companion.UPCOMING)
    fun getUpcomingMovies(
            @Query("api_key") apiKey: String?,
            @Query("page") page: Int
    ): Observable<MovieResponse?>

    @GET(HttpClientModule.Companion.POPULAR)
    fun getPopularMovies(
            @Query("api_key") apiKey: String?,
            @Query("page") page: Int
    ): Observable<MovieResponse?>

    @GET(HttpClientModule.Companion.MOVIE + "{id}/similar")
    fun getSimilarMovies(
            @Path("id") movieId: String?,
            @Query("api_key") apiKey: String?
    ): Observable<MovieResponse?>?

    @GET(HttpClientModule.Companion.MOVIE + "{id}/recommendations")
    fun getRecommendations(
            @Path("id") movieId: String?,
            @Query("api_key") apiKey: String?
    ): Observable<MovieResponse?>?

    @GET(HttpClientModule.Companion.MOVIE + "{id}/credits")
    fun getCredits(
            @Path("id") movieId: String?,
            @Query("api_key") apiKey: String?
    ): Observable<CreditsResponse?>?

    @GET(HttpClientModule.Companion.MOVIE + "{id}")
    fun getMovieDetails(
            @Path("id") movieId: String?,
            @Query("api_key") apiKey: String?
    ): Observable<MovieDetails?>?

    @GET(HttpClientModule.Companion.MOVIE + "{id}/videos")
    fun getMovieVideos(
            @Path("id") movieId: String?,
            @Query("api_key") apiKey: String?
    ): Observable<VideoResponse?>?

    @GET(HttpClientModule.Companion.PERSON + "{id}")
    fun getPerson(
            @Path("id") personId: Int,
            @Query("api_key") apiKey: String?
    ): Observable<Person?>?

    @GET(HttpClientModule.Companion.DISCOVER)
    fun getMoviesForCastID(
            @Query("with_cast") castId: Int,
            @Query("api_key") apiKey: String?
    ): Observable<MovieResponse?>?

    @GET(HttpClientModule.Companion.SEARCH_MOVIE)
    fun searchMovies(
            @Query("query") query: String?,
            @Query("api_key") apiKey: String?
    ): Observable<MovieResponse?>?
}