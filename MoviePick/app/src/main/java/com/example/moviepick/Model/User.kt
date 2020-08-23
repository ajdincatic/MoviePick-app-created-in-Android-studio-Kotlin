package com.example.moviepick.Model

import java.io.Serializable

class User:Serializable{
    var firstName: String = ""
    var lastName: String = ""
    var phone: String = ""
    var email: String = ""
    var username: String = ""
    var password: String = ""
    var passwordConfirm: String = ""
    var userTypeId: Int = 2
}