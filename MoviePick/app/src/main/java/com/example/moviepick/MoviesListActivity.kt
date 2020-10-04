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
import com.example.moviepick.Services.MovieService
import com.example.moviepick.Services.NewsService
import kotlinx.android.synthetic.main.activity_movies_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListActivity : AppCompatActivity(),
    OnItemClickListener {

    private val service = APIService.buildService(MovieService::class.java)
    private lateinit var movieAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        initRecyclerView()
        loadMovies()
    }

    private fun loadMovies(){
        val requestCall = service.getAll()
        Toast.makeText(this@MoviesListActivity,"Loading...", Toast.LENGTH_LONG).show()
        requestCall.enqueue(object : Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Toast.makeText(this@MoviesListActivity,"Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if(response.isSuccessful){
                    val list = response.body()!!
                    movieAdapter.submitList(list)
                    movieAdapter.notifyDataSetChanged()
                }
                else if(response.code() == 400){
                    Toast.makeText(this@MoviesListActivity,"400", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MoviesListActivity,"Server error", Toast.LENGTH_SHORT).show()
                }
            }
        })
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

    override fun onItemClick(item: MovieCast, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: ActorCast, position: Int) {
        TODO("Not yet implemented")
    }
}