package com.zeltixdev.photoapp.ui.photo.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeltixdev.photoapp.databinding.FragmentPhotoDetailBinding
import com.zeltixdev.photoapp.viewModels.PhotoInitParams
import com.zeltixdev.photoapp.viewModels.PhotoDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotoDetailsFragment : Fragment() {

    private val args: PhotoDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var photoDetailViewModelAssistedFactory: PhotoDetailViewModel.AssistedFactory

    private val photoDetailViewModel: PhotoDetailViewModel by viewModels {
        PhotoDetailViewModel.provideFactory(
                photoDetailViewModelAssistedFactory, PhotoInitParams(args.photoId)
        )
    }

    private var _binding: FragmentPhotoDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelCalbacks()
    }

    private fun initViewModelCalbacks() {
        photoDetailViewModel.apply {
            displayPhoto.observe(viewLifecycleOwner, { urlPhoto: String ->
                Glide.with(requireContext())
                    .load(urlPhoto)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(binding.detailImageView)
            })
            displayAuthorName.observe(viewLifecycleOwner, { name: String ->
                binding.detailTvAuthor.text = name
            })
        }
    }
}