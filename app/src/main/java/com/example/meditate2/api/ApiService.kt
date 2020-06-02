package com.example.meditate2.api

import com.example.meditate2.photoactivities.PhotoModel
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

   // @GET("5ed6823a340000b9e006daeb")
    @GET("5ed6beb33400003ed306dc33")
    fun fetchPhotos() : Call<List<PhotoModel>>
}