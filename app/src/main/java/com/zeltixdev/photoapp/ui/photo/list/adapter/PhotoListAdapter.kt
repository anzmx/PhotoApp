package com.zeltixdev.photoapp.ui.photo.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zeltixdev.photoapp.databinding.PhotoListItemBinding
import com.zeltixdev.photoapp.entity.Photo
import com.zeltixdev.photoapp.ui.photo.list.adapter.viewholder.PhotoViewHolder

class PhotoListAdapter(
    private val onPhotoIdClick: (String) -> Unit
) : RecyclerView.Adapter<PhotoViewHolder>() {

    var items: List<Photo> = ArrayList(0)
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(
            PhotoListItemBinding.inflate(inflater, parent, false),
            onPhotoIdClick
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}