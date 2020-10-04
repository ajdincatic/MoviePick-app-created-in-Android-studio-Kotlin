package com.example.moviepick.Helper

import com.example.moviepick.Model.*


interface OnItemClickListener{
    fun onItemClick(item: Movie, position: Int)
    fun onItemClick(item: Actor, position: Int)
    fun onItemClick(item: News, position: Int)
    fun onItemClick(item: MovieCast, position: Int)
    fun onItemClick(item: ActorCast, position: Int)
}