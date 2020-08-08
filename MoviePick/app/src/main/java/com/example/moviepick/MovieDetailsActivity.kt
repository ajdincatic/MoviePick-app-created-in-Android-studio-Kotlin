package com.example.moviepick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviepick.Model.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movie = intent.getSerializableExtra("MOVIE") as Movie

        movie_title.text = "${movie.title}(${movie.ReleaseDate})"
        movie_desc.text = movie.description
        movie_image.setImageResource(movie.image)
    }
}