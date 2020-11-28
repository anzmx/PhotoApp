package com.zeltixdev.photoapp.viewModels

import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.repository.PhotoRepository
import com.zeltixdev.photoapp.utilities.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

data class PhotoInitParams(val photoId: String)
class PhotoViewModel @AssistedInject constructor(
    private val photoRepository: PhotoRepository,
    @Assisted private val initParams: PhotoInitParams
): ViewModel(){
    private val _photoLiveData = MutableLiveData<Photo>()
    val photo: LiveData<Photo>
    get() = _photoLiveData

    init {
        getPhoto(initParams.photoId)
    }

    private fun getPhoto(photoId: String){
        viewModelScope.launch {
            photoRepository.getPhotoDetails(photoId).collect {
                _photoLiveData.value = it
            }
        }
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(initParams: PhotoInitParams): PhotoViewModel
    }

    companion object {
        fun provideFactory(
                assistedFactory: AssistedFactory,
                initParams: PhotoInitParams
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(initParams) as T
            }
        }
    }
}