package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Decoration.TopSpancingItemDecoration
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.*
import com.example.moviepick.Services.APIService
import com.example.moviepick.Services.ActorService
import com.example.moviepick.Services.MovieService
import kotlinx.android.synthetic.main.activity_movies_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActorsListActivity : AppCompatActivity(),
    OnItemClickListener {

    private val service = APIService.buildService(ActorService::class.java)
    private lateinit var actorAdapter: ActorRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_list)

        initRecyclerView()
        loadActors()
    }

    private fun loadActors(){
        val requestCall = service.getAll()
        Toast.makeText(this@ActorsListActivity,"Loading...", Toast.LENGTH_LONG).show()
        requestCall.enqueue(object : Callback<List<Actor>> {
            override fun onFailure(call: Call<List<Actor>>, t: Throwable) {
                Toast.makeText(this@ActorsListActivity,"Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Actor>>, response: Response<List<Actor>>) {
                if(response.isSuccessful){
                    val list = response.body()!!
                    actorAdapter.submitList(list)
                    actorAdapter.notifyDataSetChanged()
                }
                else if(response.code() == 400){
                    Toast.makeText(this@ActorsListActivity,"400", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@ActorsListActivity,"Server error", Toast.LENGTH_SHORT).show()
                }
            }
        })
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

    override fun onItemClick(item: MovieCast, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: ActorCast, position: Int) {
        TODO("Not yet implemented")
    }
}