package com.dicoding.submission.moviefavorite.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.model.TvShowBase

class TvShowFavoriteAdapter(private val activity: Activity) : RecyclerView.Adapter<TvShowViewHolder>() {

    var listTvShow = ArrayList<TvShowBase>()
        set(value) {
            if (listTvShow.size > 0) {
                this.listTvShow.clear()
            }
            this.listTvShow.addAll(value)
            notifyDataSetChanged()
        }

    fun addItem(tvShow: TvShowBase) {
        this.listTvShow.add(tvShow)
        notifyItemInserted(this.listTvShow.size - 1)
    }

    fun updateItem(position: Int, movie: TvShowBase) {
        this.listTvShow[position] = movie
        notifyItemChanged(position, movie)
    }

    fun removeItem(position: Int) {
        this.listTvShow.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listTvShow.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item_favorite_tv_show, parent, false)
        return TvShowViewHolder(root)
    }

    override fun getItemCount(): Int = this.listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(this.listTvShow[position])
    }


}