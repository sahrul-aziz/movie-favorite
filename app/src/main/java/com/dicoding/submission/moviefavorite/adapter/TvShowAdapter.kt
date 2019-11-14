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
import com.dicoding.submission.moviefavorite.model.TvShowResults
import com.dicoding.submission.moviefavorite.ui.TvShowDetailActivity
import com.dicoding.submission.moviefavorite.utils.AppConst
import com.dicoding.submission.moviefavorite.utils.AppConst.TV_SHOW_KEY
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_item_tv_show.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    companion object {
        private lateinit var favoriteHelper: FavoriteHelper
    }

    var listTvShow = ArrayList<TvShowResults>()
        set(value) {
            if (listTvShow.size > 0) {
                this.listTvShow.clear()
            }
            this.listTvShow.addAll(value)
            notifyDataSetChanged()
        }

    fun initFavoriteHelper(context: Context) {
        favoriteHelper = FavoriteHelper(context).getInstance(context)
        favoriteHelper.open()
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
                btn_tv_show_favorite.setOnClickListener {
                    val values = ContentValues()
                    values.put(DatabaseContract.FavoriteColumn.ITEM_ID, tvShow.id)
                    values.put(DatabaseContract.FavoriteColumn.TITLE, tvShow.original_name)
                    values.put(DatabaseContract.FavoriteColumn.POSTER_PATH, tvShow.poster_path)
                    values.put(DatabaseContract.FavoriteColumn.DATE, tvShow.first_air_date)
                    values.put(DatabaseContract.FavoriteColumn.POPULARITY, tvShow.popularity)
                    values.put(DatabaseContract.FavoriteColumn.SCORE, tvShow.vote_average)
                    values.put(DatabaseContract.FavoriteColumn.LANGUAGE, tvShow.original_language)
                    values.put(DatabaseContract.FavoriteColumn.OVERVIEW, tvShow.overview)
                    values.put(DatabaseContract.FavoriteColumn.ITEM_TYPE, TV_SHOW_KEY)
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