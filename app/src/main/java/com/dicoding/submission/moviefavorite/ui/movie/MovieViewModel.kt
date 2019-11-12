package com.dicoding.submission.moviefavorite.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dicoding.submission.moviefavorite.BuildConfig
import com.dicoding.submission.moviefavorite.model.ErrorResponse
import com.dicoding.submission.moviefavorite.model.MovieBase
import com.dicoding.submission.moviefavorite.model.MovieResults
import com.dicoding.submission.moviefavorite.service.MovieService
import com.dicoding.submission.moviefavorite.utils.AppConst.BASE_URL
import com.dicoding.submission.moviefavorite.utils.AppConst.MOVIE_KEY
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MovieViewModel(private val state: SavedStateHandle) : ViewModel() {

    val listMovie = MutableLiveData<ArrayList<MovieResults>>()
    var errorResponse = MutableLiveData<ErrorResponse>()

    internal fun retrieveMovie() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var locale = Locale.getDefault().toString()
        locale = locale.replace("_", "-")

        val service = retrofit.create(MovieService::class.java)
        service.getMovie(BuildConfig.API_KEY, locale).enqueue(object : Callback<MovieBase> {
            override fun onFailure(call: Call<MovieBase>, t: Throwable) {
                listMovie.postValue(null)
                val error = ErrorResponse(
                    0,
                    "Something went wrong, please try again later!",
                    false
                )
                errorResponse.postValue(error)
            }

            override fun onResponse(call: Call<MovieBase>, response: Response<MovieBase>) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            listMovie.postValue(responseBody.results)
                        }
                    }
                } else {
                    listMovie.postValue(null)
                    val error = Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                    errorResponse.postValue(error)
                }
            }
        })
    }

    fun getMovie(): ArrayList<MovieResults>? {
        return listMovie.value
    }

    fun saveMovie(movie: ArrayList<MovieResults>?) {
        state.set(MOVIE_KEY, movie)
    }
}