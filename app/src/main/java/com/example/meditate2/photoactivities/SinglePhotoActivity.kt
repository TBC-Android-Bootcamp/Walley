package com.example.meditate2.photoactivities


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_PICTURES
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide.with
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.meditate2.BuildConfig
import com.example.meditate2.R
import com.example.meditate2.savefile.ImageViewModel
import kotlinx.android.synthetic.main.activity_single_photo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.*


class SinglePhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_photo)

        val image = intent.getStringExtra("image")
        val idz = intent.getStringExtra("idz")
        with(this).load(image).into(singleImage)

        //val intent = intent
        val imageViewModel =
            ImageViewModel(
                applicationContext
            )

        FileProvider.getUriForFile(
            Objects.requireNonNull(applicationContext),
            BuildConfig.APPLICATION_ID + ".provider", File("${Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES)}/WalleyPapers/$idz.jpg"))

        saveImage.setOnClickListener {
            with(this)
                .asBitmap()
                .load(image)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        CoroutineScope(Dispatchers.IO).launch {
                            imageViewModel.downloadImage(resource, idz!!)
                        }
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {  }
                })
        }
    }
}