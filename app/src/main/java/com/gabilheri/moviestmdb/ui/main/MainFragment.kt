package com.gabilheri.moviestmdb.ui.main

import android.os.Bundle
import android.support.v17.leanback.app.BrowseSupportFragment
import android.support.v17.leanback.widget.*
import android.support.v4.content.ContextCompat
import android.util.SparseArray
import com.gabilheri.moviestmdb.App
import com.gabilheri.moviestmdb.Config
import com.gabilheri.moviestmdb.R
import com.gabilheri.moviestmdb.dagger.modules.HttpClientModule
import com.gabilheri.moviestmdb.data.Api.TheMovieDbAPI
import com.gabilheri.moviestmdb.data.models.Movie
import com.gabilheri.moviestmdb.data.models.MovieResponse
import com.gabilheri.moviestmdb.ui.base.GlideBackgroundManager
import com.gabilheri.moviestmdb.ui.movies.MoviePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/8/16.
 */
class MainFragment : BrowseSupportFragment(), OnItemViewSelectedListener {
    @kotlin.jvm.JvmField
    @Inject
    var mDbAPI: TheMovieDbAPI? = null
    private var mBackgroundManager: GlideBackgroundManager? = null
    var mRows: SparseArray<MovieRow?>? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App.Companion.instance()!!.appComponent()!!.inject(this)

        // The background manager allows us to manage a dimmed background that does not interfere with the rows
        // It is the preferred way to set the background of a fragment
        mBackgroundManager = GlideBackgroundManager(activity)

        // The brand color will be used as the background for the Headers fragment
        brandColor = ContextCompat.getColor(activity!!, R.color.primary_transparent)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // The TMDB logo on the right corner. It is necessary to show based on their API usage policy
        badgeDrawable = ContextCompat.getDrawable(activity!!, R.drawable.powered_by)
        createDataRows()
        createRows()
        prepareEntranceTransition()
        fetchNowPlayingMovies()
        fetchTopRatedMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
    }

    /**
     * Creates the data rows objects
     */
    private fun createDataRows() {
        mRows = SparseArray()
        val moviePresenter = MoviePresenter()
        mRows!!.put(NOW_PLAYING.toInt(), MovieRow()
                .setId(NOW_PLAYING)
                .setAdapter(ArrayObjectAdapter(moviePresenter))
                .setTitle("Now Playing")
                .setPage(1)
        )
        mRows!!.put(TOP_RATED.toInt(), MovieRow()
                .setId(TOP_RATED)
                .setAdapter(ArrayObjectAdapter(moviePresenter))
                .setTitle("Top Rated")
                .setPage(1)
        )
        mRows!!.put(POPULAR.toInt(), MovieRow()
                .setId(POPULAR)
                .setAdapter(ArrayObjectAdapter(moviePresenter))
                .setTitle("Popular")
                .setPage(1)
        )
        mRows!!.put(UPCOMING.toInt(), MovieRow()
                .setId(UPCOMING)
                .setAdapter(ArrayObjectAdapter(moviePresenter))
                .setTitle("Upcoming")
                .setPage(1)
        )
    }

    /**
     * Creates the rows and sets up the adapter of the fragment
     */
    private fun createRows() {
        // Creates the RowsAdapter for the Fragment
        // The ListRowPresenter tells to render ListRow objects
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        for (i in 0 until mRows!!.size()) {
            val row = mRows!![i]
            // Adds a new ListRow to the adapter. Each row will contain a collection of Movies
            // That will be rendered using the MoviePresenter
            val headerItem = HeaderItem(row!!.id, row.title)
            val listRow = ListRow(headerItem, row.adapter)
            rowsAdapter.add(listRow)
        }
        // Sets this fragments Adapter.
        // The setAdapter method is defined in the BrowseFragment of the Leanback Library
        adapter = rowsAdapter
        onItemViewSelectedListener = this
    }

    /**
     * Fetches now playing movies from TMDB
     */
    private fun fetchNowPlayingMovies() {
        mDbAPI!!.getNowPlayingMovies(Config.API_KEY_URL, mRows!![NOW_PLAYING.toInt()]!!.page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: MovieResponse? ->
                    bindMovieResponse(response!!, NOW_PLAYING.toInt())
                    startEntranceTransition()
                }) { e: Throwable -> Timber.e(e, "Error fetching now playing movies: %s", e.message) }
    }

    /**
     * Fetches the popular movies from TMDB
     */
    private fun fetchPopularMovies() {
        mDbAPI!!.getPopularMovies(Config.API_KEY_URL, mRows!![POPULAR.toInt()]!!.page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: MovieResponse? ->
                    bindMovieResponse(response!!, POPULAR.toInt())
                    startEntranceTransition()
                }) { e: Throwable -> Timber.e(e, "Error fetching popular movies: %s", e.message) }
    }

    /**
     * Fetches the upcoming movies from TMDB
     */
    private fun fetchUpcomingMovies() {
        mDbAPI!!.getUpcomingMovies(Config.API_KEY_URL, mRows!![UPCOMING.toInt()]!!.page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: MovieResponse? ->
                    bindMovieResponse(response!!, UPCOMING.toInt())
                    startEntranceTransition()
                }) { e: Throwable -> Timber.e(e, "Error fetching upcoming movies: %s", e.message) }
    }

    /**
     * Fetches the top rated movies from TMDB
     */
    private fun fetchTopRatedMovies() {
        mDbAPI!!.getTopRatedMovies(Config.API_KEY_URL, mRows!![TOP_RATED.toInt()]!!.page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: MovieResponse? ->
                    bindMovieResponse(response!!, TOP_RATED.toInt())
                    startEntranceTransition()
                }) { e: Throwable -> Timber.e(e, "Error fetching top rated movies: %s", e.message) }
    }

    /**
     * Binds a movie response to it's adapter
     * @param response
     * The response from TMDB API
     * @param id
     * The ID / position of the row
     */
    private fun bindMovieResponse(response: MovieResponse, id: Int) {
        val row = mRows!![id]
        row?.page = row?.page!! + 1
        for (m in response.results!!) {
            if (m.posterPath != null) { // Avoid showing movie without posters
                row.adapter?.add(m)
            }
        }
    }

    override fun onItemSelected(itemViewHolder: Presenter.ViewHolder, item: Any, rowViewHolder: RowPresenter.ViewHolder, row: Row) {
        // Check if the item is a movie
        if (item is Movie) {
            val movie = item
            // Check if the movie has a backdrop
            if (movie.backdropPath != null) {
                mBackgroundManager!!.loadImage(HttpClientModule.Companion.BACKDROP_URL + movie.backdropPath)
            } else {
                // If there is no backdrop for the movie we just use a default one
                mBackgroundManager!!.setBackground(ContextCompat.getDrawable(activity!!, R.drawable.material_bg))
            }
        }
    }

    companion object {
        private const val NOW_PLAYING: Long = 0
        private const val TOP_RATED: Long = 1
        private const val POPULAR: Long = 2
        private const val UPCOMING: Long = 3
    }
}