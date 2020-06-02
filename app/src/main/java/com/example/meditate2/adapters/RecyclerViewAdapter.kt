package com.example.meditate2.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meditate2.R
import com.example.meditate2.databinding.PhotoLayoutBinding
import com.example.meditate2.photoactivities.PhotoModel
import com.example.meditate2.photoactivities.PhotosActivity
import com.example.meditate2.photoactivities.SinglePhotoActivity
import kotlinx.android.synthetic.main.photo_layout.view.*


class RecyclerViewAdapter(
    private val photos: List<PhotoModel>,
    private val activity: PhotosActivity
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun getItemCount() = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PhotoLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.photo_layout, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        Glide.with(activity).load(photo.url).into(holder.image)
        holder.image.setOnClickListener {
            val intent = Intent(activity, SinglePhotoActivity::class.java)
            intent.putExtra("image", photo.url)
            intent.putExtra("idz", photo.id)
            activity.startActivity(intent)
        }

    }

    class ViewHolder(binding:PhotoLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = itemView.myImage
    }
}