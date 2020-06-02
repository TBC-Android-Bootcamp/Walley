package com.example.meditate2.photoactivities


import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meditate2.api.ApiService
import com.example.meditate2.R
import com.example.meditate2.adapters.ViewPager
import com.example.meditate2.adapters.RecyclerViewAdapter
import com.example.meditate2.databinding.ActivityPhotosBindingImpl
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_photos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PhotosActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        init()

        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, ViewPager::class.java)
            Toast.makeText(this, "You Logged out", Toast.LENGTH_SHORT).show()
            startActivity(intent)

        }
    }

    private fun init(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.mocky.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val binding: ActivityPhotosBindingImpl = DataBindingUtil.setContentView(
            this, R.layout.activity_photos)


        val api = retrofit.create(ApiService::class.java)

        api.fetchPhotos().enqueue(object : Callback<List<PhotoModel>> {
            override fun onResponse(
                call: Call<List<PhotoModel>>,
                response: Response<List<PhotoModel>>
            ) {
                d("bacho", "onResponse ${response.body()!![0].url}")
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                d("bacho", "onFailure")
            }
        })
    }

    private fun showData(photos: List<PhotoModel>) {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@PhotosActivity, 2)
            adapter = RecyclerViewAdapter(
                photos,
                this@PhotosActivity
            )
        }
    }


}

