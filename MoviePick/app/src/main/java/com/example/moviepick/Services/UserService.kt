package com.example.moviepick.Services

import com.example.moviepick.Model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {

    @GET("user")
    fun get(@Query("username") username: String? = null) : Call<List<User>>

    @POST("user")
    fun post(@Body newItem: User) : Call<User>
}