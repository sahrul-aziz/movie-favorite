package com.dicoding.submission.moviefavorite.service

import com.dicoding.submission.moviefavorite.model.MovieBase
import com.dicoding.submission.moviefavorite.model.TvShowBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie")
    fun getMovie(@Query("api_key") apiKey: String, @Query("language") language: String) : Call<MovieBase>

    @GET("tv")
    fun getTvShow(@Query("api_key") apiKey: String, @Query("language") language: String) : Call<TvShowBase>

}