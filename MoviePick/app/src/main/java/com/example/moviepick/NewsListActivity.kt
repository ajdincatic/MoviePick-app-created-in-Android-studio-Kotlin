package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviepick.Decoration.TopSpancingItemDecoration
import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.DataSource
import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.activity_movies_list.*

class NewsListActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var newsAdapter: NewsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.initNews()
        newsAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@NewsListActivity)
            val topSpacingDecoration =
                TopSpancingItemDecoration(30)
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

}