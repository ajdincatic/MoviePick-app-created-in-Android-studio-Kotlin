package com.example.moviepick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.moviepick.Model.DataSource
import com.example.moviepick.Model.Quote
import kotlinx.android.synthetic.main.activity_quote.*

var quoteList = ArrayList<Quote>()

class QuoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)

        if(quoteList.isNotEmpty()){
            txtHeadQuote.text = quoteList.random().quote
        }

        btnAddQuote.setOnClickListener {
            if(txtNewQuote.text!!.isNotEmpty()){
                val q = Quote(txtNewQuote.text.toString())
                quoteList.add(q)
                Toast.makeText(this,"Quote added.",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Please insert text.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}