package com.example.moviepick.Model

import java.io.Serializable

data class Movie(
    var title: String,
    var description: String,
    var image: Int,
    var ReleaseDate: String? = null
): Serializable