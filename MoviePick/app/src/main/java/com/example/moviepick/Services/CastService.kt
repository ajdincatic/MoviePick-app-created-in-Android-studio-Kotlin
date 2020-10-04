package com.example.moviepick.Services

import com.example.moviepick.Model.ActorCast
import com.example.moviepick.Model.MovieCast
import com.example.moviepick.Model.Quote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CastService {
    @GET("Cast")
    fun getAllActors(@Query("RoleId") roleId:Int,@Query("MovieAndTvshowId") mtvsId:Int): Call<List<MovieCast>>
    @GET("Cast")
    fun getAllMovies(@Query("RoleId") roleId:Int,@Query("PersonId") personId:Int): Call<List<ActorCast>>
}