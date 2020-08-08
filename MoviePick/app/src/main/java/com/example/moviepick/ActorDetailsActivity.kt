package com.example.moviepick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviepick.Model.Actor
import com.example.moviepick.Model.Movie
import kotlinx.android.synthetic.main.activity_actor_details.*
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.actor_list_item.*

class ActorDetailsActivity : AppCompatActivity() {

    private lateinit var actor: Actor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_details)

        actor = intent.getSerializableExtra("ACTOR") as Actor

        name.text = "${actor.firstName} ${actor.lastName}"
        about.text = actor.about
        actor_image.setImageResource(actor.image)
    }
}