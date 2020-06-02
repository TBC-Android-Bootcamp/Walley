package com.example.meditate2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.meditate2.adapters.ViewPager

class SplashScreen : AppCompatActivity() {

    private val splashTimeOut: Long = 4000 // 4s
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, ViewPager::class.java))
            finish()

        }, splashTimeOut)
    }
}
