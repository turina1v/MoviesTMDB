package com.gabilheri.moviestmdb.data.models

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/9/16.
 */
class VideoResponse {
    var id = 0
        private set
    var results: List<Video>? = null
        private set

    fun setId(id: Int): VideoResponse {
        this.id = id
        return this
    }

    fun setResults(results: List<Video>?): VideoResponse {
        this.results = results
        return this
    }
}