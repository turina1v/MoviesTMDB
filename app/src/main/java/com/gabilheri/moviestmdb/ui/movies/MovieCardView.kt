package com.gabilheri.moviestmdb.ui.movies

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gabilheri.moviestmdb.R
import com.gabilheri.moviestmdb.dagger.modules.HttpClientModule
import com.gabilheri.moviestmdb.data.models.Movie
import com.gabilheri.moviestmdb.ui.base.BindableCardView
import java.util.*

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/8/16.
 */
class MovieCardView(context: Context?) : BindableCardView<Movie>(context) {
    @JvmField
    @BindView(R.id.poster_iv)
    var posterIV: ImageView? = null

    @JvmField
    @BindView(R.id.vote_average_tv)
    var mVoteAverageTV: TextView? = null
    public override fun bind(data: Movie) {
        Glide.with(context)
                .load(HttpClientModule.POSTER_URL + data.posterPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(posterIV)
        mVoteAverageTV!!.text = String.format(Locale.getDefault(), "%.2f", data.voteAverage)
    }

    protected override val layoutResource: Int
        protected get() = R.layout.card_movie

    init {
        ButterKnife.bind(this)
    }
}