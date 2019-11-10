package com.dicoding.submission.moviefavorite.db

import android.provider.BaseColumns

internal object DatabaseContract {

    var MOVIE_TABLE_NAME = "movie"
    var TV_SHOW_TABLE_NAME = "tv_show"

    internal class MovieColumn : BaseColumns {
        companion object {
            const val _ID = "_id"
            const val TITLE = "title"
            const val POSTER_PATH = "poster_path"
            const val RELEASE_DATE = "release_date"
            const val POPULARITY = "popularity"
            const val SCORE = "score"
            const val LANGUAGE = "language"
            const val OVERVIEW = "overview"
        }
    }

    internal class TvShowColumn : BaseColumns {
        companion object {
            const val _ID = "_id"
            const val TITLE = "title"
            const val POSTER_PATH = "poster_path"
            const val FIRST_AIR_DATE = "first_air_date"
            const val POPULARITY = "popularity"
            const val SCORE = "score"
            const val LANGUAGE = "language"
            const val OVERVIEW = "overview"
        }
    }
}