package com.gabilheri.moviestmdb.ui.movies

import android.support.v17.leanback.widget.Presenter
import android.view.ViewGroup
import com.gabilheri.moviestmdb.data.models.Movie

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/8/16.
 */
class MoviePresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(MovieCardView(parent.context))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        (viewHolder.view as MovieCardView).bind(item as Movie)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}