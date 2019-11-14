package com.dicoding.submission.moviefavorite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.submission.moviefavorite.db.DatabaseContract.FavoriteColumn
import com.dicoding.submission.moviefavorite.db.DatabaseContract.FavoriteColumn.Companion.FAVORITE_TABLE_NAME

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "catalogue"
        private const val DATABASE_VERSION = 2
        private var CREATE_TABLE_FAVORITE = "CREATE TABLE $FAVORITE_TABLE_NAME (" +
                "${FavoriteColumn._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${FavoriteColumn.ITEM_ID} INTEGER UNIQUE," +
                "${FavoriteColumn.TITLE} TEXT NOT NULL," +
                "${FavoriteColumn.POSTER_PATH} TEXT NOT NULL," +
                "${FavoriteColumn.DATE} TEXT NOT NULL," +
                "${FavoriteColumn.POPULARITY} INTEGER," +
                "${FavoriteColumn.SCORE} FLOAT," +
                "${FavoriteColumn.LANGUAGE} TEXT NOT NULL," +
                "${FavoriteColumn.OVERVIEW} TEXT NOT NULL," +
                "${FavoriteColumn.ITEM_TYPE} TEXT NOT NULL" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        print(CREATE_TABLE_FAVORITE)
        db.execSQL(CREATE_TABLE_FAVORITE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $FAVORITE_TABLE_NAME")
        onCreate(db)
    }
}