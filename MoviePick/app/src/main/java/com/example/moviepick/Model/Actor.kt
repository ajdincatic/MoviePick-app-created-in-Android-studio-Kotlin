package com.example.moviepick.Model

import java.io.Serializable

class Actor(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var photo: String,
    var biography: String,
    var dateOfBirth: String,
    var dateOfDeath: String
): Serializable