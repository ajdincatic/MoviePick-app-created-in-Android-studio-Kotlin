package com.example.moviepick

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
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
            val intent = Intent(this,LoginActivity::class.java)

            val options = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions.makeSceneTransitionAnimation(this,img,"logo_image")
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP")
            }

            startActivity(intent,options.toBundle())
            finish()
        }
        Handler().postDelayed(r, 2500)
    }
}