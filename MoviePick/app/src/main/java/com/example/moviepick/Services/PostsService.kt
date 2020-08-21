package com.example.moviepick.Services

import com.example.moviepick.Model.Posts
import retrofit2.Call
import retrofit2.http.*

interface PostsService {

    @GET("posts")
    fun getAll(@Query("title") title: String? = null): Call<List<Posts>>
    //fun get(@QueryMap filter: HashMap<String?, Int?>? = null): Call<List<Posts>>

    @GET("posts/{id}")
    fun getById(@Path("id") id:Int): Call<Posts>

    @POST("posts")
    fun post(@Body newItem:Posts): Call<Posts>

}