package com.example.meditate2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.meditate2.adapters.ViewPager

class SplashScreen : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    private val splashTimeOut: Long = 4000 // 4s
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            init()

        }, splashTimeOut)
    }
    
    private fun init(){
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if(currentUser != null) {
            val intent = Intent(this, PhotosActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            startActivity(Intent(this, ViewPager::class.java))
            finish()
        }
    }
}
