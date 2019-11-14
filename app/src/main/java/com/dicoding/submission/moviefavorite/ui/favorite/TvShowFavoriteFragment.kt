package com.dicoding.submission.moviefavorite.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.adapter.TvShowFavoriteAdapter
import com.dicoding.submission.moviefavorite.db.FavoriteHelper
import com.dicoding.submission.moviefavorite.helper.TvShowMappingHelper
import com.dicoding.submission.moviefavorite.model.TvShowResults
import com.dicoding.submission.moviefavorite.utils.AppConst.TV_SHOW_KEY
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_tv_show.view.*
import kotlinx.android.synthetic.main.tab_favorite_tv_show.view.*
import kotlinx.coroutines.*

class TvShowFavoriteFragment : Fragment() {

    private lateinit var root: View
    private lateinit var favoriteHelper: FavoriteHelper
    private lateinit var tvShowFavAdapter: TvShowFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.tab_favorite_tv_show, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowFavAdapter = TvShowFavoriteAdapter()
        tvShowFavAdapter.notifyDataSetChanged()
        favoriteHelper = FavoriteHelper(root.context).getInstance(root.context)
        favoriteHelper.open()

        root.rv_favorite_tv_show.layoutManager = LinearLayoutManager(this.context)
        root.rv_favorite_tv_show.adapter = tvShowFavAdapter

        if (savedInstanceState != null) {
            val listTvShow = savedInstanceState.getParcelableArrayList<TvShowResults>(TV_SHOW_KEY)
            tvShowFavAdapter.listTvShow = listTvShow as ArrayList<TvShowResults>
        } else {
            loadTvShowAsync()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(TV_SHOW_KEY, tvShowFavAdapter.listTvShow)
    }

    private fun loadTvShowAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            root.progressbar_fav_tv_show.visibility = View.VISIBLE
            delay(1500)
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = favoriteHelper.queryByItemType(TV_SHOW_KEY)
                TvShowMappingHelper.mapCursorToArrayList(cursor)
            }
            root.progressbar_fav_tv_show.visibility = View.INVISIBLE
            val tvShow = deferredNotes.await()
            if (tvShow.size > 0) {
                tvShowFavAdapter.listTvShow = tvShow
            } else {
                tvShowFavAdapter.listTvShow = ArrayList()
                root.tv_show_empty.text = resources.getString(R.string.no_favorite_tv_show)
            }
        }
    }
}