package com.example.moviepick

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.News
import kotlinx.android.synthetic.main.news_list_item.view.*
import java.lang.Byte.decode
import java.util.*
import kotlin.collections.ArrayList

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
        val title: TextView = itemView.newsTitle

        fun bind(news: News, action: OnItemClickListener){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            val imageByteArray: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(news.coverPhoto)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageByteArray)
                .into(image)
            title.text = news.title

            itemView.setOnClickListener {
                action.onItemClick(news, adapterPosition)
            }
        }

    }
}