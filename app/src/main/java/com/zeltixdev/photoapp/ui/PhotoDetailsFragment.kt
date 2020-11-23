package com.zeltixdev.photoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeltixdev.photoapp.R
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.viewModels.PhotoListViewModel
import com.zeltixdev.photoapp.viewModels.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class PhotoDetailsFragment : Fragment() {
    private val photoDetailsViewModel: PhotoViewModel by viewModels()
    private lateinit var tv_autor:TextView
    private lateinit var iv:ImageView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_autor = view.findViewById(R.id.detail_tvAuthor)
        iv = view.findViewById(R.id.detail_imageView)
        photoDetailsViewModel.res.observe(viewLifecycleOwner, Observer { photo:Photo ->
            tv_autor.text=photo.author
            Glide.with(requireContext()).load(photo.download_url).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(iv)
        })
    }
}