package com.dicoding.submission.moviefavorite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.dicoding.submission.moviefavorite.db.DatabaseContract.FavoriteColumn.Companion.FAVORITE_TABLE_NAME
import com.dicoding.submission.moviefavorite.db.DatabaseContract.FavoriteColumn.Companion.ITEM_TYPE
import com.dicoding.submission.moviefavorite.db.DatabaseContract.FavoriteColumn.Companion._ID
import java.sql.SQLException

class FavoriteHelper(context: Context) {

    private val databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private var FAVORITE_TABLE = FAVORITE_TABLE_NAME
        private var INSTANCE: FavoriteHelper? = null
    }

    fun getInstance(context: Context): FavoriteHelper {
        if (INSTANCE == null) {
            synchronized(SQLiteOpenHelper::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = FavoriteHelper(context)
                }
            }
        }
        return INSTANCE as FavoriteHelper
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
        print(FAVORITE_TABLE)
        return database.query(
            "favorite",
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC")
    }

    fun queryById(id: String): Cursor {
        return database.query(
            FAVORITE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    fun queryByItemType(itemType: String): Cursor {
        return database.query(
            FAVORITE_TABLE,
            null,
            "$ITEM_TYPE = ?",
            arrayOf(itemType),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(FAVORITE_TABLE, null, values)
    }

    fun deleteById(id: String): Int {
        return database.delete(FAVORITE_TABLE, "$_ID = '$id'", null)
    }

}