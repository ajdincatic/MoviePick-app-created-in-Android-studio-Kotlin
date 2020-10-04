package com.example.moviepick

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.*
import com.example.moviepick.Services.APIService
import com.example.moviepick.Services.CastService
import com.example.moviepick.Services.MovieService
import kotlinx.android.synthetic.main.activity_actor_details.*
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.actor_list_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ActorDetailsActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var movieAdapter: ActorDetailsMovieRecyclerAdapter
    private lateinit var actor: Actor
    private val service = APIService.buildService(CastService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_details)

        actor = intent.getSerializableExtra("ACTOR") as Actor

        if(actor.dateOfDeath == null){
            name.text = "${actor.firstName} ${actor.lastName}(${actor.dateOfBirth.substring(0,4)}-)"
        }
        else{
            name.text = "${actor.firstName} ${actor.lastName}(${actor.dateOfBirth.substring(0,4)}-${actor.dateOfDeath.substring(0,4)})"
        }
        about.text = actor.biography

        val imageByteArray: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(actor.photo)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
        actor_image.setImageBitmap(bitmap)

        initRecyclerView()
        loadMovies()
    }

    private fun loadMovies(){
        val requestCall = service.getAllMovies(1,actor.id)
        requestCall.enqueue(object : Callback<List<ActorCast>> {
            override fun onFailure(call: Call<List<ActorCast>>, t: Throwable) {
                Toast.makeText(this@ActorDetailsActivity,"Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<ActorCast>>, response: Response<List<ActorCast>>) {
                if(response.isSuccessful){
                    val list = response.body()!!
                    movieAdapter.submitList(list)
                    movieAdapter.notifyDataSetChanged()
                }
                else if(response.code() == 400){
                    Toast.makeText(this@ActorDetailsActivity,"400", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@ActorDetailsActivity,"Server error", Toast.LENGTH_SHORT).show()
                }
            }
        })
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
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE",item.movieAndTvshow)
        startActivity(intent)
    }
}