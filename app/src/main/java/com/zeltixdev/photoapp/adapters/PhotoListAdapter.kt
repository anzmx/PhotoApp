package com.zeltixdev.photoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeltixdev.photoapp.R
import com.zeltixdev.photoapp.adapters.PhotoListAdapter.PhotoViewHolder
import com.zeltixdev.photoapp.models.Photo
import kotlinx.android.synthetic.main.photo_list_item.view.*

class PhotoListAdapter(private val onPhotoClicked: (Photo) -> Unit):RecyclerView.Adapter<PhotoViewHolder>() {

    var data: List<Photo> = ArrayList(0)
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.photo_list_item, parent, false)
        return PhotoViewHolder(viewHolder){
            onPhotoClicked(data[it])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindItems(data[position])
    }


    class PhotoViewHolder(itemView: View, onPhotoClicked: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onPhotoClicked(adapterPosition)
            }
        }
        fun bindItems(photo: Photo) {
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            itemView.tvAuthor?.text = photo.author
            Glide.with(itemView).load(photo.download_url).placeholder(circularProgressDrawable)
                .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().centerCrop()
                .into(itemView.iv!!)
        }
    }
}