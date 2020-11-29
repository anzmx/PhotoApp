package com.zeltixdev.photoapp.ui.photo.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeltixdev.photoapp.entity.Photo
import com.zeltixdev.photoapp.networking.Resource
import com.zeltixdev.photoapp.repository.PhotoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhotoListViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _displayPhotoList = MutableLiveData<Resource<List<Photo>>>()
    val displayPhotoList: LiveData<Resource<List<Photo>>> get() = _displayPhotoList

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            photoRepository.getPhotos().collect {
                _displayPhotoList.value = it
            }
        }
    }
}