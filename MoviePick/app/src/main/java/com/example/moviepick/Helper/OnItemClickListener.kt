package com.example.moviepick.Helper

import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News


interface OnItemClickListener{
    fun onItemClick(item: Movie, position: Int)
    fun onItemClick(item: Actor, position: Int)
    fun onItemClick(item: News, position: Int)
}