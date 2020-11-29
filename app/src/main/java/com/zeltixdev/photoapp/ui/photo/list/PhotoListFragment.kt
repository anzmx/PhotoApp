package com.zeltixdev.photoapp.ui.photo.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.zeltixdev.photoapp.databinding.FragmentPhotoListBinding
import com.zeltixdev.photoapp.networking.Status
import com.zeltixdev.photoapp.ui.photo.list.adapter.PhotoListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment : Fragment() {

    private val photosViewModel: PhotoListViewModel by viewModels()
    private lateinit var photoListAdapter: PhotoListAdapter

    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUiElements()
        initViewModelCallbacks()
    }

    private fun initUiElements() {
        photoListAdapter = PhotoListAdapter { photoId ->
            val action = PhotoListFragmentDirections.actionListFragmentToDetailFragment(photoId)
            findNavController().navigate(action)
        }

        binding.photoList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.photoList.adapter = photoListAdapter
    }

    private fun initViewModelCallbacks() {
        photosViewModel.displayPhotoList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                Status.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                }
                Status.SUCCESS -> {
                    photoListAdapter.items = it.data!!
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        })
    }
}