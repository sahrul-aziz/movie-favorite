package com.dicoding.submission.moviefavorite.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

class AppPreference(context: Context) {

    companion object {
        private const val PREFS_NAME = "CataloguePref"
        private const val FIRST_RUN = "first_run"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    var firstRun: Boolean?
        get() = prefs.getBoolean(FIRST_RUN, true)
        set(input) {
            prefs.edit {
                putBoolean(FIRST_RUN, false)
            }
        }
}