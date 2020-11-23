package com.zeltixdev.photoapp.viewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.repository.PhotoRepository
import kotlinx.coroutines.launch

class PhotoListViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _res = MutableLiveData<List<Photo>>()

    val res : LiveData<List<Photo>>
        get() = _res

    init {
        getPhotos()
    }

    private fun getPhotos()  = viewModelScope.launch {
            _res.value = photoRepository.getPhotos()
        }
    }