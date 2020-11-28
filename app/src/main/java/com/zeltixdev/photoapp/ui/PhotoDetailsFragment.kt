package com.zeltixdev.photoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeltixdev.photoapp.R
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.viewModels.PhotoInitParams
import com.zeltixdev.photoapp.viewModels.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class PhotoDetailsFragment : Fragment() {
    private val args: PhotoDetailsFragmentArgs by navArgs()
    @Inject lateinit var photoViewModelAssistedFactory: PhotoViewModel.AssistedFactory
    private val photoViewModel: PhotoViewModel by viewModels {
        PhotoViewModel.provideFactory(
            photoViewModelAssistedFactory, PhotoInitParams(args.photoId)
        )
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoViewModel.photo.observe(viewLifecycleOwner, { photo:Photo -> detail_tvAuthor.text = photo.author
        Glide.with(requireContext()).load(photo.download_url).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(detail_imageView)})
    }
}