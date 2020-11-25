package com.zeltixdev.photoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeltixdev.photoapp.R
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.ui.PhotoListFragmentDirections
import kotlinx.android.synthetic.main.photo_list_item.view.*

class PhotoListAdapter(private val ctx: Context, val data:List<Photo>):RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(ctx).inflate(R.layout.photo_list_item,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindItems(data[position])
    }

}
class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bindItems(photo:Photo){
        itemView.tvAuthor?.text=photo.author
        Glide.with(itemView).load(photo.download_url).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().centerCrop().into(itemView.iv!!)
        itemView.setOnClickListener {
        val action = PhotoListFragmentDirections.actionListFragmentToDetailFragment(photo.id)
            it.findNavController().navigate(action)
        }
    }
}