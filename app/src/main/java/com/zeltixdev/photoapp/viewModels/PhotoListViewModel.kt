package com.zeltixdev.photoapp.viewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.repository.PhotoRepository
import com.zeltixdev.photoapp.utilities.Resource
import kotlinx.coroutines.launch

class PhotoListViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
): ViewModel(){
   val photos: LiveData<Resource<List<Photo>>> = photoRepository.getPhotos().asLiveData()
    }