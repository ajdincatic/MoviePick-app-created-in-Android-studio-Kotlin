package com.example.moviepick

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.*
import com.example.moviepick.Services.APIService
import com.example.moviepick.Services.ActorService
import com.example.moviepick.Services.CastService
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailsActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var actorAdapter: MovieDetailsActorRecyclerAdapter
    private lateinit var movie: Movie
    private val service = APIService.buildService(CastService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movie = intent.getSerializableExtra("MOVIE") as Movie

        movie_title.text = "${movie.title}(${movie.releaseDate!!.substring(0,4)})"
        movie_desc.text = movie.description

        val imageByteArray: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(movie.poster)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        var bitmap: Bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
        movie_image.setImageBitmap(bitmap)

        initRecyclerView()
        loadActors()
    }

    private fun loadActors(){
        val requestCall = service.getAllActors(1,movie.id)
        Toast.makeText(this@MovieDetailsActivity,"Loading actors...", Toast.LENGTH_SHORT).show()
        requestCall.enqueue(object : Callback<List<MovieCast>> {
            override fun onFailure(call: Call<List<MovieCast>>, t: Throwable) {
                Toast.makeText(this@MovieDetailsActivity,"Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<MovieCast>>, response: Response<List<MovieCast>>) {
                if(response.isSuccessful){
                    val list = response.body()!!
                    actorAdapter.submitList(list)
                    actorAdapter.notifyDataSetChanged()
                }
                else if(response.code() == 400){
                    Toast.makeText(this@MovieDetailsActivity,"400", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MovieDetailsActivity,"Server error", Toast.LENGTH_SHORT).show()
                }
            }
        })
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

    }

    override fun onItemClick(item: News, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: MovieCast, position: Int) {
        val intent = Intent(this, ActorDetailsActivity::class.java)
        intent.putExtra("ACTOR",item.person)
        startActivity(intent)
    }

    override fun onItemClick(item: ActorCast, position: Int) {
        TODO("Not yet implemented")
    }
}