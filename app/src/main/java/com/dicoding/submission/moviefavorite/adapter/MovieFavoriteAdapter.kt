package com.dicoding.submission.moviefavorite.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.model.MovieResults

class MovieFavoriteAdapter(private val activity: Activity) : RecyclerView.Adapter<MovieViewHolder>() {

    var listMovie = ArrayList<MovieResults>()
        set(value) {
            if (listMovie.size > 0) {
                this.listMovie.clear()
            }
            this.listMovie.addAll(value)
            notifyDataSetChanged()
        }

    fun addItem(movie: MovieResults) {
        this.listMovie.add(movie)
        notifyItemInserted(this.listMovie.size - 1)
    }

    fun updateItem(position: Int, movie: MovieResults) {
        this.listMovie[position] = movie
        notifyItemChanged(position, movie)
    }

    fun removeItem(position: Int) {
        this.listMovie.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listMovie.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item_favorite_movie, parent, false)
        return MovieViewHolder(root)
    }

    override fun getItemCount(): Int = this.listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(this.listMovie[position])
    }

}