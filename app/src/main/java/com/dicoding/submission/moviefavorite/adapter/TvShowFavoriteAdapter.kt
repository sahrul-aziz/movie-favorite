package com.dicoding.submission.moviefavorite.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.model.TvShowResults
import com.dicoding.submission.moviefavorite.ui.TvShowDetailActivity
import com.dicoding.submission.moviefavorite.utils.AppConst
import kotlinx.android.synthetic.main.list_item_tv_show.view.*

class TvShowFavoriteAdapter : RecyclerView.Adapter<TvShowFavoriteAdapter.TvShowViewHolder>() {

    var listTvShow = ArrayList<TvShowResults>()
        set(value) {
            if (listTvShow.size > 0) {
                this.listTvShow.clear()
            }
            this.listTvShow.addAll(value)
            notifyDataSetChanged()
        }

    fun addItem(tvShow: TvShowResults) {
        this.listTvShow.add(tvShow)
        notifyItemInserted(this.listTvShow.size - 1)
    }

    fun updateItem(position: Int, tvShow: TvShowResults) {
        this.listTvShow[position] = tvShow
        notifyItemChanged(position, tvShow)
    }

    fun removeItem(position: Int) {
        this.listTvShow.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listTvShow.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.list_item_tv_show, parent, false)
        return TvShowViewHolder(root)
    }

    override fun getItemCount(): Int = this.listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(this.listTvShow[position])
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowResults) {
            with(itemView) {
                tv_show_title.text = tvShow.original_name
                var overview: String? = resources.getString(R.string.overview_not_available)
                if (tvShow.overview != "") {
                    overview = tvShow.overview
                }
                tv_show_overview.text = overview
                val score = "Score: ${tvShow.vote_average}"
                tv_show_score.text = score
                Glide.with(context).load("${AppConst.IMAGE_URL}/w185${tvShow.poster_path}").into(tv_show_poster)
                setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                    intent.putExtra(AppConst.TV_SHOW_KEY, tvShow)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}