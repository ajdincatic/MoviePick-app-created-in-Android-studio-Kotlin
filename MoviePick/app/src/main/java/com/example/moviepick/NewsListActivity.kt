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
import com.example.moviepick.Services.NewsService
import com.example.moviepick.Services.QuoteService
import kotlinx.android.synthetic.main.activity_movies_list.*
import kotlinx.android.synthetic.main.activity_quote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListActivity : AppCompatActivity(),
    OnItemClickListener {

    private val service = APIService.buildService(NewsService::class.java)
    private lateinit var newsAdapter: NewsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        initRecyclerView()
        loadNews()
    }

    private fun loadNews(){
        val requestCall = service.getAll()
        Toast.makeText(this@NewsListActivity,"Loading...", Toast.LENGTH_LONG).show()
        requestCall.enqueue(object : Callback<List<News>> {
            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                Toast.makeText(this@NewsListActivity,"Error: ${t.toString()}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if(response.isSuccessful){
                    val list = response.body()!!
                    newsAdapter.submitList(list)
                    newsAdapter.notifyDataSetChanged()
                }
                else if(response.code() == 400){
                    Toast.makeText(this@NewsListActivity,"400", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@NewsListActivity,"Server error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@NewsListActivity)
            val topSpacingDecoration =
                TopSpancingItemDecoration(15)
            addItemDecoration(topSpacingDecoration)
            newsAdapter = NewsRecyclerAdapter(this@NewsListActivity)
            adapter = newsAdapter
        }
    }

    override fun onItemClick(item: Movie, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: Actor, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: News, position: Int) {
        val intent = Intent(this, NewsDetailsActivity::class.java)
        intent.putExtra("NEWS",item)
        startActivity(intent)
    }

    override fun onItemClick(item: MovieCast, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(item: ActorCast, position: Int) {
        TODO("Not yet implemented")
    }

}