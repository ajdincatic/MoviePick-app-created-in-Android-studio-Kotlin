package com.example.moviepick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Model.DataSource
import com.example.moviepick.Model.TopSpancingItemDecoration
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        movieAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MoviesListActivity)
            val topSpacingDecoration = TopSpancingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            movieAdapter = MovieRecyclerAdapter()
            adapter = movieAdapter
        }
    }
}