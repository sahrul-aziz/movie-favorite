package com.dicoding.submission.moviefavorite.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.adapter.MovieAdapter
import com.dicoding.submission.moviefavorite.model.ErrorResponse
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie.view.*

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_movie, container, false)
        movieAdapter = MovieAdapter()
        movieAdapter.notifyDataSetChanged()
        movieAdapter.initFavoriteHelper(this.requireContext())
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        root.rv_movie.layoutManager = LinearLayoutManager(this.context)
        root.rv_movie.adapter = movieAdapter

        movieViewModel = ViewModelProviders.of(this, SavedStateVMFactory(this@MovieFragment)).get(MovieViewModel::class.java)
        movieViewModel.listMovie.observe(this, Observer {
            if (it != null) {
                root.movie_empty.visibility = View.GONE
                movieAdapter.listMovie = it
            } else {
                root.movie_empty.text = resources.getString(R.string.no_movie)
                root.movie_empty.visibility = View.VISIBLE
            }
            showLoading(false)
        })

        movieViewModel.errorResponse.observe(this, Observer {
            if (it != null) {
                showSnackbar(it)
                movieViewModel.errorResponse.value = null
            }
        })

        val movie = movieViewModel.getMovie()
        if (movie == null) {
            showLoading(true)
            movieViewModel.retrieveMovie()
        } else {
            movieAdapter.listMovie = movie
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        movieViewModel.saveMovie(movieViewModel.listMovie.value)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            root.progressBarMovie.visibility = View.VISIBLE
        } else {
            root.progressBarMovie.visibility = View.GONE
        }
    }

    private fun showSnackbar(errorResponse: ErrorResponse){
        Snackbar.make(root, "Error [code: ${errorResponse.statusCode}]: ${errorResponse.statusMessage}", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        movieAdapter.closeFavoriteHelper()
    }
}