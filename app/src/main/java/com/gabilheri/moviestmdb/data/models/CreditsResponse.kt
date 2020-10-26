package com.gabilheri.moviestmdb.data.models

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/9/16.
 */
class CreditsResponse {
    var id = 0
        private set
    var cast: List<CastMember>? = null
        private set
    var crew: List<CrewMember>? = null
        private set

    fun setId(id: Int): CreditsResponse {
        this.id = id
        return this
    }

    fun setCast(cast: List<CastMember>?): CreditsResponse {
        this.cast = cast
        return this
    }

    fun setCrew(crew: List<CrewMember>?): CreditsResponse {
        this.crew = crew
        return this
    }
}