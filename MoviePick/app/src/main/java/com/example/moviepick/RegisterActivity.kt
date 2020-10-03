package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.moviepick.Model.Quote
import com.example.moviepick.Model.User
import com.example.moviepick.Services.APIService
import com.example.moviepick.Services.QuoteService
import com.example.moviepick.Services.UserService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_quote.*
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private val service = APIService.buildService(UserService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btnBackToLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSignUp.setOnClickListener {
            if( txtRegFirstName.text!!.isNotEmpty() && txtRegLastName.text!!.isNotEmpty()
                && txtRegUsername.text!!.isNotEmpty() && txtRegEmail.text!!.isNotEmpty()
                && txtRegPhone.text!!.isNotEmpty() && txtRegPassword.text!!.isNotEmpty()) {

                var u: User = User()
                u.firstName = txtRegFirstName.text.toString()
                u.lastName = txtRegLastName.text.toString()
                u.username = txtRegUsername.text.toString()
                u.email = txtRegEmail.text.toString()
                u.phone = txtRegPhone.text.toString()
                u.password = txtRegPassword.text.toString()
                u.passwordConfirm = txtRegPassword.text.toString()
                u.userTypeId = 1

                val requestCall = service.post(u)
                requestCall.enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            val newItem = response.body()!!
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this@RegisterActivity, "User added", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Server error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Server error" + t.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
            else{
                Toast.makeText(this@RegisterActivity, "All fields are required.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}