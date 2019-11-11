package com.dicoding.submission.moviefavorite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.submission.moviefavorite.db.DatabaseContract.MOVIE_TABLE_NAME
import com.dicoding.submission.moviefavorite.db.DatabaseContract.MovieColumn.Companion._ID
import java.sql.SQLException

class MovieHelper(context: Context) {

    companion object {
        private var MOVIE_TABLE = MOVIE_TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: MovieHelper? = null
        private lateinit var database: SQLiteDatabase
    }

    fun getInstance(context: Context): MovieHelper {
        if (INSTANCE == null) {
            synchronized(SQLiteOpenHelper::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = MovieHelper(context)
                }
            }
        }
        return INSTANCE as MovieHelper
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()
        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            MOVIE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC")
    }

    fun queryById(id: String): Cursor {
        return database.query(
            MOVIE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(MOVIE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(MOVIE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(MOVIE_TABLE, "$_ID = '$id'", null)
    }

}