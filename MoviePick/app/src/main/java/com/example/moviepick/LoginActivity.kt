package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.moviepick.Model.Quote
import com.example.moviepick.Model.User
import com.example.moviepick.Services.APIService
import com.example.moviepick.Services.QuoteService
import com.example.moviepick.Services.UserService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_quote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val service = APIService.buildService(UserService::class.java)

    var username: TextView? = null
    var password: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btnLogin.setOnClickListener {
            if(txtUsername.text!!.isNotEmpty() && txtPassword.text!!.isNotEmpty()){
                val requestCall = service.get(txtUsername!!.text.toString())
                requestCall.enqueue(object : Callback<List<User>> {
                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Toast.makeText(this@LoginActivity,"Error",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                        if(response.isSuccessful){
                            val list = response.body()!!
                            if(list.count() > 0){
                                APIService.username = txtUsername!!.text.toString()
                                APIService.password = txtPassword!!.text.toString()
                                APIService.loggedUser = list[0]
                                APIService.setHeader()
                                val intent = Intent(this@LoginActivity,MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(this@LoginActivity,"Wrong username or password.",Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this@LoginActivity,"Server error",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
            else{
                Toast.makeText(this,"Insert username and password!",Toast.LENGTH_SHORT).show()
            }
        }

        btnNewAccount.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}