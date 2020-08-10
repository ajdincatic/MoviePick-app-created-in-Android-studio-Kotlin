package com.example.moviepick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviepick.Model.Movie
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsRecyclerAdapter(var clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NewsViewHolder -> {
                holder.bind(items[position], clickListener)
            }
        }
    }

    fun submitList(newsList: List<News>){
        items = newsList
    }

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.newsImage
        val tite: TextView = itemView.newsTitle

        fun bind(news: News, action: OnItemClickListener){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(news.image)
                .into(image)
            tite.text = news.title

            itemView.setOnClickListener {
                action.onItemClick(news, adapterPosition)
            }
        }
    }
}