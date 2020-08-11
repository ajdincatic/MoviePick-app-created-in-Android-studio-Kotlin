package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Model.DataSource
import com.example.moviepick.Model.Movie
import com.example.moviepick.Decoration.TopSpancingItemDecoration
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : AppCompatActivity(),
    OnItemClickListener {

    private lateinit var movieAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.initMovies()
        movieAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MoviesListActivity)
            val topSpacingDecoration =
                TopSpancingItemDecoration(15)
            addItemDecoration(topSpacingDecoration)
            movieAdapter = MovieRecyclerAdapter(this@MoviesListActivity)
            adapter = movieAdapter
        }
    }

    override fun onItemClick(item: Movie, position: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE",item)
        startActivity(intent)
    }

    override fun onItemClick(item: Actor, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: News, position: Int) {
        TODO("Not yet implemented")
    }
}