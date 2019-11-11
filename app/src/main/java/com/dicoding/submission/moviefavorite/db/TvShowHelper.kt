package com.dicoding.submission.moviefavorite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.submission.moviefavorite.db.DatabaseContract.TV_SHOW_TABLE_NAME
import com.dicoding.submission.moviefavorite.db.DatabaseContract.TvShowColumn.Companion._ID
import java.sql.SQLException

class TvShowHelper(context: Context) {

    companion object {
        private var TV_SHOW_TABLE = TV_SHOW_TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: TvShowHelper? = null
        private lateinit var database: SQLiteDatabase
    }

    fun getInstance(context: Context): TvShowHelper {
        if (INSTANCE == null) {
            synchronized(SQLiteOpenHelper::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = TvShowHelper(context)
                }
            }
        }
        return INSTANCE as TvShowHelper
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
            TV_SHOW_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC")
    }

    fun queryById(id: String): Cursor {
        return database.query(
            TV_SHOW_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(TV_SHOW_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(TV_SHOW_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(TV_SHOW_TABLE, "$_ID = '$id'", null)
    }

}