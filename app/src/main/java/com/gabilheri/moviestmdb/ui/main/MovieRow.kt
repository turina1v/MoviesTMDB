package com.gabilheri.moviestmdb.ui.main

import android.support.v17.leanback.widget.ArrayObjectAdapter

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/11/16.
 */
data class MovieRow(
        var page: Int = 0,
        var id: Long = 0,
        var adapter: ArrayObjectAdapter? = null,
        var title: String? = null
) {
    fun setPage(page: Int): MovieRow {
        this.page = page
        return this
    }

    fun setId(id: Long): MovieRow {
        this.id = id
        return this
    }

    fun setAdapter(adapter: ArrayObjectAdapter?): MovieRow {
        this.adapter = adapter
        return this
    }

    fun setTitle(title: String?): MovieRow {
        this.title = title
        return this
    }
}