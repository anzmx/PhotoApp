package com.zeltixdev.photoapp.ui.photo.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeltixdev.photoapp.databinding.PhotoListItemBinding
import com.zeltixdev.photoapp.entity.Photo

private const val STROKE = 5f
private const val CENTER_RADIUS = 30f

class PhotoViewHolder(
    private val binding: PhotoListItemBinding,
    onPhotoIdClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var photoId: String
    private val circularProgressDrawable: CircularProgressDrawable

    init {
        binding.root.setOnClickListener {
            onPhotoIdClick(photoId)
        }
        circularProgressDrawable = CircularProgressDrawable(binding.root.context).apply {
            strokeWidth = STROKE
            centerRadius = CENTER_RADIUS
            start()
        }
    }

    fun bindItem(photo: Photo) {
        photoId = photo.id

        binding.tvAuthor.text = photo.author
        Glide.with(binding.ivImage)
            .load(photo.download_url)
            .placeholder(circularProgressDrawable)
            .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().centerCrop()
            .into(binding.ivImage)
    }
}