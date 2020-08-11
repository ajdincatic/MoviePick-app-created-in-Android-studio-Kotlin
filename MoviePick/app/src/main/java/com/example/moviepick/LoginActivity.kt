package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.moviepick.Model.DataSource
import com.example.moviepick.Model.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val users = DataSource.initUser()
    var username: TextView? = null
    var password: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = txtUsername
        password = txtPassword

        btnLogin.setOnClickListener {
            Login()
        }
    }

    private fun Login(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}