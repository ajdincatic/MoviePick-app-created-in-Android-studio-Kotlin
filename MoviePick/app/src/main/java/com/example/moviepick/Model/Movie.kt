package com.example.moviepick.Model

import java.io.Serializable

data class Movie(
    var id: Int,
    var title: String,
    var description: String,
    var poster: String,
    var releaseDate: String? = null,
    var runningTime: String? = null,
    var calculatedRating: String? = null
): Serializable