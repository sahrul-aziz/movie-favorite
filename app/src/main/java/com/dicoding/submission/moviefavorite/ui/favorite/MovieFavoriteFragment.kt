package com.dicoding.submission.moviefavorite.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.adapter.MovieFavoriteAdapter
import com.dicoding.submission.moviefavorite.db.FavoriteHelper
import com.dicoding.submission.moviefavorite.helper.MovieMappingHelper
import com.dicoding.submission.moviefavorite.model.MovieResults
import com.dicoding.submission.moviefavorite.utils.AppConst.MOVIE_KEY
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.tab_favorite_movie.view.*
import kotlinx.coroutines.*

class MovieFavoriteFragment : Fragment() {

    private lateinit var root: View
    private lateinit var favoriteHelper: FavoriteHelper
    private lateinit var movieFavAdapter: MovieFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.tab_favorite_movie, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieFavAdapter = MovieFavoriteAdapter()
        movieFavAdapter.notifyDataSetChanged()
        favoriteHelper = FavoriteHelper(root.context).getInstance(root.context)
        favoriteHelper.open()

        root.rv_favorite_movie.layoutManager = LinearLayoutManager(this.context)
        root.rv_favorite_movie.adapter = movieFavAdapter

        if (savedInstanceState != null) {
            val listMovie = savedInstanceState.getParcelableArrayList<MovieResults>(MOVIE_KEY)
            movieFavAdapter.listMovie = listMovie as ArrayList<MovieResults>
        } else {
            loadMovieAsync()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(MOVIE_KEY, movieFavAdapter.listMovie)
    }

    private fun loadMovieAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            root.progressbar_fav_movie.visibility = View.VISIBLE
            delay(1500)
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = favoriteHelper.queryByItemType(MOVIE_KEY)
                MovieMappingHelper.mapCursorToArrayList(cursor)
            }
            root.progressbar_fav_movie.visibility = View.INVISIBLE
            val movies = deferredNotes.await()
            if (movies.size > 0) {
                movieFavAdapter.listMovie = movies
            } else {
                movieFavAdapter.listMovie = ArrayList()
                root.movie_empty.text = resources.getString(R.string.no_favorite_movie)
            }
        }
    }
}