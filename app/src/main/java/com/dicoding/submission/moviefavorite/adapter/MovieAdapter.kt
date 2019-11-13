package com.dicoding.submission.moviefavorite.adapter

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.db.DatabaseContract
import com.dicoding.submission.moviefavorite.db.FavoriteHelper
import com.dicoding.submission.moviefavorite.model.MovieResults
import com.dicoding.submission.moviefavorite.ui.MovieDetailActivity
import com.dicoding.submission.moviefavorite.utils.AppConst
import com.dicoding.submission.moviefavorite.utils.AppConst.MOVIE_KEY
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object {
        private lateinit var favoriteHelper: FavoriteHelper
    }

    var listMovie = ArrayList<MovieResults>()
        set(value) {
            if (listMovie.size > 0) {
                this.listMovie.clear()
            }
            this.listMovie.addAll(value)
            notifyDataSetChanged()
        }

    fun initFavoriteHelper(context: Context) {
        favoriteHelper = FavoriteHelper(context).getInstance(context)
        favoriteHelper.open()
    }

    fun closeFavoriteHelper() {
        favoriteHelper.close()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(root)
    }

    override fun getItemCount(): Int = this.listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(this.listMovie[position])
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieResults) {
            with(itemView) {
                movie_title.text = movie.title
                var overview: String? = resources.getString(R.string.overview_not_available)
                if (movie.overview != "") {
                    overview = movie.overview
                }
                movie_overview.text = overview
                val score = "Score: ${movie.vote_average}"
                movie_score.text = score
                Glide.with(context).load("${AppConst.IMAGE_URL}/w185${movie.poster_path}").into(movie_poster)
                setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(AppConst.MOVIE_KEY, movie)
                    itemView.context.startActivity(intent)
                }
                btn_movie_favorite.setOnClickListener {
                    val values = ContentValues()
                    values.put(DatabaseContract.FavoriteColumn.TITLE, movie.title)
                    values.put(DatabaseContract.FavoriteColumn.POSTER_PATH, movie.poster_path)
                    values.put(DatabaseContract.FavoriteColumn.DATE, movie.release_date)
                    values.put(DatabaseContract.FavoriteColumn.POPULARITY, movie.popularity)
                    values.put(DatabaseContract.FavoriteColumn.OVERVIEW, movie.overview)
                    values.put(DatabaseContract.FavoriteColumn.LANGUAGE, movie.original_language)
                    values.put(DatabaseContract.FavoriteColumn.OVERVIEW, movie.overview)
                    values.put(DatabaseContract.FavoriteColumn.ITEM_TYPE, MOVIE_KEY)
                    val result = favoriteHelper.insert(values)
                    if (result > 0) {
                        Snackbar.make(itemView, resources.getString(R.string.save_success), Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(itemView, resources.getString(R.string.save_failed), Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}