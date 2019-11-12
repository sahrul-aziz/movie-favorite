package com.dicoding.submission.moviefavorite.ui.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dicoding.submission.moviefavorite.BuildConfig
import com.dicoding.submission.moviefavorite.model.ErrorResponse
import com.dicoding.submission.moviefavorite.model.TvShowBase
import com.dicoding.submission.moviefavorite.model.TvShowResults
import com.dicoding.submission.moviefavorite.service.MovieService
import com.dicoding.submission.moviefavorite.utils.AppConst
import com.dicoding.submission.moviefavorite.utils.AppConst.TV_SHOW_KEY
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class TvShowViewModel(private val state: SavedStateHandle) : ViewModel() {

    val listTvShow = MutableLiveData<ArrayList<TvShowResults>>()
    var errorResponse = MutableLiveData<ErrorResponse>()

    internal fun retrieveTvShow() {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var locale = Locale.getDefault().toString()
        locale = locale.replace("_", "-")

        val service = retrofit.create(MovieService::class.java)
        service.getTvShow(BuildConfig.API_KEY, locale).enqueue(object : Callback<TvShowBase> {
            override fun onFailure(call: Call<TvShowBase>, t: Throwable) {
                listTvShow.postValue(null)
                val error = ErrorResponse(
                    0,
                    "Something went wrong, please try again later!",
                    false
                )
                errorResponse.postValue(error)
            }

            override fun onResponse(call: Call<TvShowBase>, response: Response<TvShowBase>) {
                if (response.code() == 200) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        listTvShow.postValue(responseBody.results)
                    }
                } else {
                    listTvShow.postValue(null)
                    val error = Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                    errorResponse.postValue(error)
                }
            }
        })
    }

    fun getTvShow(): ArrayList<TvShowResults>? {
        return state.get(TV_SHOW_KEY)
    }

    fun saveTvShow(tvShow: ArrayList<TvShowResults>?) {
        state.set(TV_SHOW_KEY, tvShow)
    }
}