package com.example.meditate2.adapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.meditate2.R
import com.example.meditate2.auth.LoginFragment
import com.example.meditate2.auth.RegisterFragment


class ViewPager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val pagerAdapter =
            PagerAdapter(
                supportFragmentManager,
                1
            )

        pagerAdapter.addFragment(LoginFragment())
        pagerAdapter.addFragment(RegisterFragment())

        viewPager.adapter = pagerAdapter
    }
}