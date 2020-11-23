package com.zeltixdev.photoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        photosViewModel.photos.observe(viewLifecycleOwner, {
            when(it.status){
                Status.LOADING -> progressBar.visibility = View.VISIBLE
                Status.ERROR -> {
                    progressBar.visibility = View.INVISIBLE
                    Log.d("PhotoDeatailError: ", it.message!!)
                }
                Status.SUCCESS -> {
                    recyclerView.adapter = PhotoListAdapter(requireContext(),it.data!!)
                    progressBar.visibility = View.INVISIBLE
                }
            }
        })
       //replace navigation on image press
        //view.findViewById<Button>(R.id.button_first).setOnClickListener {
         //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        //}
    }
}