package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btnLogin.setOnClickListener {
            if(txtUsername.text!!.isNotEmpty() || txtPassword.text!!.isNotEmpty()){
                if(txtUsername.text!!.startsWith("admin") && txtPassword.text!!.startsWith("admin")){
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Wrong username or password!",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Insert username and password!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}