package com.dicoding.submission.moviefavorite.helper

import android.database.Cursor
import com.dicoding.submission.moviefavorite.db.DatabaseContract
import com.dicoding.submission.moviefavorite.model.MovieResults
import com.dicoding.submission.moviefavorite.model.TvShowResults

object TvShowMappingHelper {

    fun mapCursorToArrayList(tvShowCursor: Cursor): ArrayList<TvShowResults> {
        val tvShowList = ArrayList<TvShowResults>()
        tvShowCursor.moveToFirst()
        while (tvShowCursor.moveToNext()) {
            val id = tvShowCursor.getInt(tvShowCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn._ID))
            val title = tvShowCursor.getString(tvShowCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.TITLE))
            val posterPath = tvShowCursor.getString(tvShowCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POSTER_PATH))
            val date = tvShowCursor.getString(tvShowCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.DATE))
            val popularity = tvShowCursor.getDouble(tvShowCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POPULARITY))
            val score = tvShowCursor.getDouble(tvShowCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.SCORE))
            val language = tvShowCursor.getString(tvShowCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.LANGUAGE))
            val overview = tvShowCursor.getString(tvShowCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.OVERVIEW))
            tvShowList.add(TvShowResults(
                id = id,
                original_name = title,
                poster_path = posterPath,
                first_air_date = date,
                popularity = popularity,
                vote_average = score,
                original_language = language,
                overview = overview
            ))
        }
        return tvShowList
    }
}