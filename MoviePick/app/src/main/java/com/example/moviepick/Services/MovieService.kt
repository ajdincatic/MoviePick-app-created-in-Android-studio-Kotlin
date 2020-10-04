package com.example.moviepick.Services

import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {
    @GET("MovieAndTvShow")
    fun getAll(): Call<List<Movie>>
}