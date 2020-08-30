package com.example.moviepick

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.DataSource
import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var actorAdapter: MovieDetailsActorRecyclerAdapter
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movie = intent.getSerializableExtra("MOVIE") as Movie

        movie_title.text = "${movie.title}(${movie.ReleaseDate})"
        movie_desc.text = movie.description
        movie_image.setImageResource(movie.image)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.initActors()
        actorAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        rvMovieDetailsActors.apply {
            val horizontalLayoutManager =
                LinearLayoutManager(this@MovieDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = horizontalLayoutManager
            actorAdapter = MovieDetailsActorRecyclerAdapter(this@MovieDetailsActivity)
            adapter = actorAdapter
        }
    }

    override fun onItemClick(item: Movie, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: Actor, position: Int) {
        val intent = Intent(this, ActorDetailsActivity::class.java)
        intent.putExtra("ACTOR",item)
        startActivity(intent)
    }

    override fun onItemClick(item: News, position: Int) {
        TODO("Not yet implemented")
    }
}