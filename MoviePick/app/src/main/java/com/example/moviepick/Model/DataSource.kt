package com.example.moviepick.Model

import com.example.moviepick.R

class DataSource{

    companion object{

        fun createDataSet(): ArrayList<Movie>{
            val list = ArrayList<Movie>()
            list.add(
                Movie(
                    "Titanic",
                    "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.",
                    R.drawable.titanic
                )
            )
            list.add(
                Movie(
                    "Gladiator",
                    "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.",
                    R.drawable.gladiator
                )
            )
            return list
        }
    }
}