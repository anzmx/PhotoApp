package com.zeltixdev.photoapp.viewModels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.repository.PhotoRepository
import com.zeltixdev.photoapp.utilities.Resource
import kotlinx.coroutines.launch

class PhotoViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val photoId: MutableLiveData<String> =
        savedStateHandle.getLiveData("photoId", "")
    private val _res = MutableLiveData<Photo>()

    val res : LiveData<Photo>
        get() = _res

    init {
        getPhotoDetails()
    }

    private fun getPhotoDetails()  = viewModelScope.launch {
       _res.value = photoRepository.getPhotoDetails(photoId.value!!)
    }

}