package com.dicoding.submission.moviefavorite.db

import android.provider.BaseColumns

internal object DatabaseContract {

    internal class FavoriteColumn : BaseColumns {
        companion object {
            const val FAVORITE_TABLE_NAME = "favorite"
            const val _ID = "_id"
            const val TITLE = "title"
            const val POSTER_PATH = "poster_path"
            const val DATE = "release_date"
            const val POPULARITY = "popularity"
            const val SCORE = "score"
            const val LANGUAGE = "language"
            const val OVERVIEW = "overview"
            const val ITEM_TYPE = "item_type"
        }
    }
}