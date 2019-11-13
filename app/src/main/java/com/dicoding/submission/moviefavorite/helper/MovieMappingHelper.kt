package com.dicoding.submission.moviefavorite.helper

import android.database.Cursor
import com.dicoding.submission.moviefavorite.db.DatabaseContract
import com.dicoding.submission.moviefavorite.model.MovieResults

object MovieMappingHelper {

    fun mapCursorToArrayList(moviesCursor: Cursor): ArrayList<MovieResults> {
        val movieList = ArrayList<MovieResults>()
        moviesCursor.moveToFirst()
        while (moviesCursor.moveToNext()) {
            val id = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn._ID))
            val title = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.TITLE))
            val posterPath = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POSTER_PATH))
            val date = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.DATE))
            val popularity = moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.POPULARITY))
            val score = moviesCursor.getDouble(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.SCORE))
            val language = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.LANGUAGE))
            val overview = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumn.OVERVIEW))
            movieList.add(MovieResults(
                id = id,
                title = title,
                poster_path = posterPath,
                release_date = date,
                popularity = popularity,
                vote_average = score,
                original_language = language,
                overview = overview
            ))
        }
        return movieList
    }
}