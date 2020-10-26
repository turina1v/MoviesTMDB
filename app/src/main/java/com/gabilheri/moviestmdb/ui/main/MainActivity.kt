package com.gabilheri.moviestmdb.ui.main

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.gabilheri.moviestmdb.R

/**
 * Created by [Marcus Gabilheri](mailto:marcus@gabilheri.com)
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/8/16.
 */
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}