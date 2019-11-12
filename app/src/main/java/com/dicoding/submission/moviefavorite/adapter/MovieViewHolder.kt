package com.dicoding.submission.moviefavorite.adapter

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.model.MovieResults
import com.dicoding.submission.moviefavorite.utils.AppConst.IMAGE_URL
import com.dicoding.submission.moviefavorite.utils.AppConst.MOVIE_KEY
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: MovieResults) {
        with(itemView) {
            movie_title.text = movie.title
            var overview: String = resources.getString(R.string.overview_not_available)
            if (movie.overview != "") {
                overview = movie.overview
            }
            movie_overview.text = overview
            val score = "Score: ${movie.vote_average}"
            movie_score.text = score
            Glide.with(context).load("${IMAGE_URL}/w185${movie.poster_path}").into(movie_poster)
            setOnClickListener {
                Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
//                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
//                intent.putExtra(MOVIE_KEY, movie)
//                itemView.context.startActivity(intent)
            }
        }
    }
}