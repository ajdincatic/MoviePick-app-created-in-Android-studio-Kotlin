package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.DataSource
import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.activity_actor_details.*
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.actor_list_item.*

class ActorDetailsActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var movieAdapter: ActorDetailsMovieRecyclerAdapter
    private lateinit var actor: Actor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_details)

        actor = intent.getSerializableExtra("ACTOR") as Actor

        name.text = "${actor.firstName} ${actor.lastName}"
        about.text = actor.about
        actor_image.setImageResource(actor.image)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.initMovies()
        movieAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        rvActorDetailsMovies.apply {
            val horizontalLayoutManager =
                LinearLayoutManager(this@ActorDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = horizontalLayoutManager
            movieAdapter = ActorDetailsMovieRecyclerAdapter(this@ActorDetailsActivity)
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