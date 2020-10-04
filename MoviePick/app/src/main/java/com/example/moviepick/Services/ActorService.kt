package com.example.moviepick.Services

import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ActorService {
    @GET("Person")
    fun getAll(): Call<List<Actor>>
}