package com.example.moviepick

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.Movie
import kotlinx.android.synthetic.main.actor_list_item.view.*
import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.android.synthetic.main.movie_list_item.view.blog_image

class ActorRecyclerAdapter(var clickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<Actor> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.actor_list_item, parent, false)
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

    fun submitList(movieList: List<Actor>){
        items = movieList
    }

    class ActorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.image
        val name: TextView = itemView.txtName
        val about: TextView = itemView.txtAbout

        fun bind(actor: Actor, action: OnItemClickListener){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(actor.image)
                .into(image)
            name.text = """${actor.firstName} ${actor.lastName}"""
            about.text = actor.about

            itemView.setOnClickListener {
                action.onItemClick(actor, adapterPosition)
            }
        }
    }
}
