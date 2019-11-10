package com.dicoding.submission.moviefavorite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.submission.moviefavorite.db.DatabaseContract.MOVIE_TABLE_NAME
import com.dicoding.submission.moviefavorite.db.DatabaseContract.MovieColumn
import com.dicoding.submission.moviefavorite.db.DatabaseContract.TV_SHOW_TABLE_NAME
import com.dicoding.submission.moviefavorite.db.DatabaseContract.TvShowColumn

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "catalogue"
        private const val DATABASE_VERSION = 1
        private var CREATE_TABLE_MOVIE_FAVORITE = "CREATE TABLE $MOVIE_TABLE_NAME (" +
                "${MovieColumn._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${MovieColumn.TITLE} TEXT NOT NULL," +
                "${MovieColumn.POSTER_PATH} TEXT NOT NULL," +
                "${MovieColumn.RELEASE_DATE} TEXT NOT NULL," +
                "${MovieColumn.POPULARITY} INTEGER," +
                "${MovieColumn.SCORE} FLOAT," +
                "${MovieColumn.LANGUAGE} TEXT NOT NULL," +
                "${MovieColumn.OVERVIEW} TEXT NOT NULL" +
                ")"

        private var CREATE_TABLE_TV_SHOW_FAVORITE = "CREATE TABLE $TV_SHOW_TABLE_NAME (" +
                "${TvShowColumn._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TvShowColumn.TITLE} TEXT NOT NULL," +
                "${TvShowColumn.POSTER_PATH} TEXT NOT NULL," +
                "${TvShowColumn.FIRST_AIR_DATE} TEXT NOT NULL," +
                "${MovieColumn.POPULARITY} INTEGER," +
                "${MovieColumn.SCORE} FLOAT," +
                "${MovieColumn.LANGUAGE} TEXT NOT NULL," +
                "${MovieColumn.OVERVIEW} TEXT NOT NULL" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_MOVIE_FAVORITE)
        db.execSQL(CREATE_TABLE_TV_SHOW_FAVORITE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $MOVIE_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TV_SHOW_TABLE_NAME")
        onCreate(db)
    }
}