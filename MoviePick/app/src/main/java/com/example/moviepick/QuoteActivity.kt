package com.example.moviepick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviepick.Model.Posts
import com.example.moviepick.Services.PostsService
import com.example.moviepick.Services.APIService
import kotlinx.android.synthetic.main.activity_quote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuoteActivity : AppCompatActivity() {

    private val service = APIService.buildService(PostsService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)

        btnAddQuote.setOnClickListener {
            if(txtNewQuote.text!!.isNotEmpty()){
                val post = Posts()
                post.title = txtNewQuote.text.toString()
                post.completed = true
                post.userId = 1

                sendPost(post)
                Toast.makeText(this,"Quote added.",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Please insert text.",Toast.LENGTH_SHORT).show()
            }
        }

        loadPosts()
    }

    private fun loadPosts(){
        val requestCall = service.getAll()
        requestCall.enqueue(object :  Callback<List<Posts>>{
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if(response.isSuccessful){
                    val list = response.body()!!
                    txtHeadQuote.text = list.random().title
                }
            }
        })
    }

    private fun sendPost(post: Posts){
        val requestCall = service.post(post)
        requestCall.enqueue(object :  Callback<Posts>{
            override fun onFailure(call: Call<Posts>, t: Throwable) {
            }

            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if(response.isSuccessful){
                    val newItem = response.body()!!
                    txtHeadQuote.text = newItem.title
                }
            }
        })
    }
}