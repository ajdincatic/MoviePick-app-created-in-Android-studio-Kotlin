package com.example.moviepick

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.example.moviepick.Services.APIService
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        txtProfileFullName.text = APIService.loggedUser!!.firstName + " "+APIService.loggedUser!!.lastName
        txtProfileUsername.text = APIService.loggedUser!!.username
        txtProfilePhone.text = APIService.loggedUser!!.phone
        txtProfileEmail.text = APIService.loggedUser!!.email
    }
}