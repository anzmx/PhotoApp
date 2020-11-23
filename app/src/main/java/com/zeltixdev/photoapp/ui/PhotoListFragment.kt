package com.zeltixdev.photoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeltixdev.photoapp.R
import com.zeltixdev.photoapp.adapters.PhotoListAdapter
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.utilities.Resource
import com.zeltixdev.photoapp.utilities.Status
import com.zeltixdev.photoapp.viewModels.PhotoListViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class PhotoListFragment : Fragment() {
    private val photosViewModel: PhotoListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        photosViewModel.photos.observe(viewLifecycleOwner, {
            when(it.status){
                Status.LOADING -> Log.d("Loading: ", "loading")
                Status.ERROR -> Log.d("PhotoDeatailError: ", it.message!!)
                Status.SUCCESS -> recyclerView.adapter = PhotoListAdapter(requireContext(),it.data!!)
            }
        })
       //replace navigation on image press
        //view.findViewById<Button>(R.id.button_first).setOnClickListener {
         //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        //}
    }
}