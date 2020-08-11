package com.example.moviepick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_splash)

        val animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        img.startAnimation(animation)

        val r = Runnable {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        Handler().postDelayed(r, 2500)
    }
}