package com.example.moviepick

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_news_details.*
import java.util.*

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        news = intent.getSerializableExtra("NEWS") as News

        newsDetailsTitle.text = news.title
        newsDetailsContent.text = news.content
        val imageByteArray: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(news.coverPhoto)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
        newsDetailsImage.setImageBitmap(bitmap)

        newsDetailsDateTimeAuthor.text = "Date & Time: "+news.dateTimeOfNews + " Author: "+news.author!!.username
    }
}