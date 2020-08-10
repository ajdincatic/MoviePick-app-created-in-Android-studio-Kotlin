package com.example.moviepick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_news_details.*

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        news = intent.getSerializableExtra("NEWS") as News

        newsDetailsTitle.text = news.title
        newsDetailsContent.text = news.content
        newsDetailsImage.setImageResource(news.image)
    }
}