package com.example.meditate2

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        var context: Context? = null
        val appInstance by lazy {
            this
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}