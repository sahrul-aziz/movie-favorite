package com.dicoding.submission.moviefavorite.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowBase(
    val page : Int = 0,
    val total_results : Int = 0,
    val total_pages : Int = 0,
    val results : ArrayList<TvShowResults> = arrayListOf()
) : Parcelable