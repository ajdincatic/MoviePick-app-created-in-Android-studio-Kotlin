package com.example.moviepick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviepick.Model.Posts
import com.example.moviepick.Model.Quote
import com.example.moviepick.Services.PostsService
import com.example.moviepick.Services.APIService
import com.example.moviepick.Services.QuoteService
import kotlinx.android.synthetic.main.activity_quote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuoteActivity : AppCompatActivity() {

    private val service = APIService.buildService(QuoteService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)

        btnAddQuote.setOnClickListener {
            if(txtNewQuote.text!!.isNotEmpty()){
                val quote = Quote()
                quote.quoteText = txtNewQuote.text.toString()
                quote.movieAndTvshowId = 1

                sendQuote(quote)
                Toast.makeText(this,"Quote added.",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Please insert text.",Toast.LENGTH_SHORT).show()
            }
        }

        loadQuote()
    }

    private fun loadQuote(){
        val requestCall = service.getAll()
        requestCall.enqueue(object :  Callback<List<Quote>>{
            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                Toast.makeText(this@QuoteActivity,"Error: ${t.toString()}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Quote>>, response: Response<List<Quote>>) {
                if(response.isSuccessful){
                    val list = response.body()!!
                    txtHeadQuote.text = list.random().quoteText
                }
                else if(response.code() == 400){
                    Toast.makeText(this@QuoteActivity,"401",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@QuoteActivity,"Server error",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun sendQuote(quote: Quote){
        val requestCall = service.post(quote)
        requestCall.enqueue(object :  Callback<Quote>{
            override fun onFailure(call: Call<Quote>, t: Throwable) {
            }

            override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                if(response.isSuccessful){
                    val newItem = response.body()!!
                    txtHeadQuote.text = newItem.quoteText
                }
                else{
                    Toast.makeText(this@QuoteActivity,"Server error",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}