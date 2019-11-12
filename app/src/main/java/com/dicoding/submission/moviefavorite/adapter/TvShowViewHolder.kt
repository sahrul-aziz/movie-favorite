package com.dicoding.submission.moviefavorite.adapter

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.moviefavorite.R
import com.dicoding.submission.moviefavorite.model.TvShowBase
import com.dicoding.submission.moviefavorite.model.TvShowResults
import com.dicoding.submission.moviefavorite.utils.AppConst.IMAGE_URL
import com.dicoding.submission.moviefavorite.utils.AppConst.TV_SHOW_KEY
import kotlinx.android.synthetic.main.list_item_tv_show.view.*

class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(tvShow: TvShowResults) {
        with(itemView) {
            tv_show_title.text = tvShow.original_name
            var overview: String = resources.getString(R.string.overview_not_available)
            if (tvShow.overview != "") {
                overview = tvShow.overview
            }
            tv_show_overview.text = overview
            val score = "Score: ${tvShow.vote_average}"
            tv_show_score.text = score
            Glide.with(context).load("${IMAGE_URL}/w185${tvShow.poster_path}").into(tv_show_poster)
            setOnClickListener {
                Toast.makeText(context, tvShow.original_name, Toast.LENGTH_SHORT).show()
//                val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
//                intent.putExtra(TV_SHOW_KEY, tvShow)
//                itemView.context.startActivity(intent)
            }
        }
    }
}