package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Decoration.TopSpancingItemDecoration
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.DataSource
import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.activity_movies_list.*

class ActorsListActivity : AppCompatActivity(),
    OnItemClickListener {

    private lateinit var actorAdapter: ActorRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_list)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.initActors()
        actorAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@ActorsListActivity)
            val topSpacingDecoration =
                TopSpancingItemDecoration(15)
            addItemDecoration(topSpacingDecoration)
            actorAdapter = ActorRecyclerAdapter(this@ActorsListActivity)
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