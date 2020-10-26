package com.gabilheri.moviestmdb.data.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/8/16.
 */
class MovieResponse : Parcelable {
    var page = 0
        private set
    var results: List<Movie>? = null
        private set

    @Json(name = "total_pages")
    var totalPages = 0
        private set

    @Json(name = "total_results")
    var totalResults = 0
        private set

    fun setPage(page: Int): MovieResponse {
        this.page = page
        return this
    }

    fun setResults(results: List<Movie>?): MovieResponse {
        this.results = results
        return this
    }

    fun setTotalPages(totalPages: Int): MovieResponse {
        this.totalPages = totalPages
        return this
    }

    fun setTotalResults(totalResults: Int): MovieResponse {
        this.totalResults = totalResults
        return this
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(page)
        dest.writeTypedList(results)
        dest.writeInt(totalPages)
        dest.writeInt(totalResults)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        page = `in`.readInt()
        results = `in`.createTypedArrayList(Movie.Companion.CREATOR)
        totalPages = `in`.readInt()
        totalResults = `in`.readInt()
    }

    companion object {
        val CREATOR: Parcelable.Creator<MovieResponse> = object : Parcelable.Creator<MovieResponse> {
            override fun createFromParcel(source: Parcel): MovieResponse? {
                return MovieResponse(source)
            }

            override fun newArray(size: Int): Array<MovieResponse?> {
                return arrayOfNulls(size)
            }
        }
    }
}