package com.example.moviepick.Model

import com.example.moviepick.R

var listMovies = ArrayList<Movie>()
var listActors = ArrayList<Actor>()

class DataSource{
    companion object{
        fun initMovies(): ArrayList<Movie>{
            val list = ArrayList<Movie>()
            list.add(
                Movie(
                    "Titanic",
                    "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.",
                    R.drawable.titanic,
                    "1999"
                )
            )
            list.add(
                Movie(
                    "Gladiator",
                    "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.",
                    R.drawable.gladiator,
                    "2000"
                )
            )
            listMovies = list
            return list
        }

        fun initActors(): ArrayList<Actor>{
            var list = ArrayList<Actor>()
            list.add(
                Actor(
                    "Leonardo",
                    "Dicaprio",
                    R.drawable.dicaprio,
                    "Few actors in the world have had a career quite as diverse as Leonardo DiCaprio's. DiCaprio has gone from relatively humble beginnings, as a supporting cast member of the sitcom Growing Pains (1985) and low budget horror movies, such as Critters 3 (1991), to a major teenage heartthrob in the 1990s, as the hunky lead actor in movies such as Romeo +..."
                )
            )
            listActors = list
            return list
        }

        fun initActorMovies(): ArrayList<Actor_Movie>{
            var list = ArrayList<Actor_Movie>()
            list.add(
                Actor_Movie(
                    movie = listMovies[0],
                    actor = listActors[0]
                )
            )
            return list
        }
    }
}