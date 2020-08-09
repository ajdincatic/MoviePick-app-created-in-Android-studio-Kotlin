package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMoviesList.setOnClickListener {
            val intent = Intent(this,MoviesListActivity::class.java)
            startActivity(intent)
        }

        btnMoviesList.setOnClickListener {
            val intent = Intent(this,MoviesListActivity::class.java)
            startActivity(intent)
        }
        btnActorsList.setOnClickListener {
            val intent = Intent(this,ActorsListActivity::class.java)
            startActivity(intent)
        }
        btnAbout.setOnClickListener {
            val intent = Intent(this,AboutActivity::class.java)
            startActivity(intent)
        }
    }
}