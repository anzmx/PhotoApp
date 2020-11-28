package com.zeltixdev.photoapp.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.repository.PhotoRepository
import com.zeltixdev.photoapp.utilities.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhotoListViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
): ViewModel(){
   private val _photosLiveData = MutableLiveData<Resource<List<Photo>>>()

           val photos: LiveData<Resource<List<Photo>>>
           get() = _photosLiveData

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            photoRepository.getPhotos().collect {
                _photosLiveData.value = it
            }
        }
    }
    }