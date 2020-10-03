package com.example.moviepick.Model

import java.io.Serializable
import java.util.*

class News: Serializable{
    var dateTimeOfNews: String = ""
    var coverPhoto: String = ""
    var title: String = ""
    var content: String= ""
    var authorId: Int=0
    var author: User?=null
}