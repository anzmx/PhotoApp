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
    private val photoId: MutableLiveData<Int> =
        savedStateHandle.getLiveData("photoId", 0)
    private val _res = MutableLiveData<Resource<Photo>>()

    val res : LiveData<Resource<Photo>>
        get() = _res

    init {
        getPhotoDetails()
    }

    private fun getPhotoDetails()  = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        photoRepository.getPhotoDetails(photoId.value!!).let {
            if (it.isSuccessful){
                _res.postValue(Resource.success(it.body()))
            }else{
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}