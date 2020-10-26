package com.gabilheri.moviestmdb.data.models

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/9/16.
 */
class Video {
    var id: String? = null
        private set
    var key: String? = null
        private set
    var site: String? = null
        private set
    var type: String? = null
        private set
    var name: String? = null
        private set

    fun setId(id: String?): Video {
        this.id = id
        return this
    }

    fun setKey(key: String?): Video {
        this.key = key
        return this
    }

    fun setSite(site: String?): Video {
        this.site = site
        return this
    }

    fun setType(type: String?): Video {
        this.type = type
        return this
    }

    fun setName(name: String?): Video {
        this.name = name
        return this
    }
}