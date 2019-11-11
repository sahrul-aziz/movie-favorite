package com.dicoding.submission.moviefavorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.model.MovieBase

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieViewHolder>() {

    var listMovie = ArrayList<MovieBase>()
        set(value) {
            if (listMovie.size > 0) {
                this.listMovie.clear()
            }
            this.listMovie.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(root)
    }

    override fun getItemCount(): Int = this.listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(this.listMovie[position])
    }

}