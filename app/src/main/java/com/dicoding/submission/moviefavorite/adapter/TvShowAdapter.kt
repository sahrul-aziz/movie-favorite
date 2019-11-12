package com.dicoding.submission.moviefavorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.model.TvShowBase
import com.dicoding.submission.moviefavorite.model.TvShowResults

class TvShowAdapter : RecyclerView.Adapter<TvShowViewHolder>() {

    var listTvShow = ArrayList<TvShowResults>()
        set(value) {
            if (listTvShow.size > 0) {
                this.listTvShow.clear()
            }
            this.listTvShow.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item_tv_show, parent, false)
        return TvShowViewHolder(root)
    }

    override fun getItemCount(): Int = this.listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(this.listTvShow[position])
    }

}