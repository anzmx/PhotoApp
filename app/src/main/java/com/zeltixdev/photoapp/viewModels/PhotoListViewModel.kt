package com.zeltixdev.photoapp.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zeltixdev.photoapp.models.PhotoList
import com.zeltixdev.photoapp.repository.PhotoRepository
import com.zeltixdev.photoapp.utilities.Resource
import kotlinx.coroutines.launch

class PhotoListViewModel @ViewModelInject constructor(
    private val photoRepository: PhotoRepository,
): ViewModel(){
    private val _res = MutableLiveData<Resource<PhotoList>>()

    val res : LiveData<Resource<PhotoList>>
        get() = _res

    init {
        getPhotos()
    }

    private fun getPhotos()  = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        photoRepository.getPhotos().let {
            if (it.isSuccessful){
                _res.postValue(Resource.success(it.body()))
            }else{
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}