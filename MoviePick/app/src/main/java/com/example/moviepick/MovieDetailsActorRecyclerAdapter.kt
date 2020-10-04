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
import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.MovieCast
import kotlinx.android.synthetic.main.actor_list_item.view.*
import kotlinx.android.synthetic.main.actor_moviedetails_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class MovieDetailsActorRecyclerAdapter(var clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<MovieCast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.actor_moviedetails_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ActorViewHolder -> {
                holder.bind(items[position], clickListener)
            }
        }
    }

    fun submitList(movieList: List<MovieCast>){
        items = movieList
    }

    class ActorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.imgMovieDetailsActorImage
        val name: TextView = itemView.txtMovieDetailsActorName

        fun bind(actor: MovieCast, action: OnItemClickListener){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            val imageByteArray: ByteArray = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Base64.getDecoder().decode(actor.person.photo)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageByteArray)
                .into(image)
            name.text = """${actor.person.firstName} ${actor.person.lastName}"""

            itemView.setOnClickListener {
                action.onItemClick(actor, adapterPosition)
            }
        }
    }
}
