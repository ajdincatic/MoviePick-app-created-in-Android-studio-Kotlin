package com.example.moviepick

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviepick.Helper.OnItemClickListener
import com.example.moviepick.Model.ActorCast
import com.example.moviepick.Model.Movie
import kotlinx.android.synthetic.main.movie_actordetails_list_item.view.*
import kotlinx.android.synthetic.main.movie_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class ActorDetailsMovieRecyclerAdapter(var clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<ActorCast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_actordetails_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MovieViewHolder -> {
                holder.bind(items[position], clickListener)
            }
        }
    }

    fun submitList(movieList: List<ActorCast>){
        items = movieList
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val image:ImageView = itemView.imgActorDetailsMovieImage
        val title:TextView = itemView.txtActorDetailsMovieName

        fun bind(movie: ActorCast, action: OnItemClickListener){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            val imageByteArray: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(movie.movieAndTvshow.poster)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageByteArray)
                .into(image)
            title.text = movie.movieAndTvshow.title


            itemView.setOnClickListener {
                action.onItemClick(movie, adapterPosition)
            }
        }
    }
}
