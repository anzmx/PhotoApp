package com.zeltixdev.photoapp.viewModels

import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.zeltixdev.photoapp.repository.PhotoRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

data class PhotoInitParams(val photoId: String)

class PhotoDetailViewModel @AssistedInject constructor(
    private val photoRepository: PhotoRepository,
    @Assisted private val initParams: PhotoInitParams
): ViewModel(){

    private val _displayPhoto = MutableLiveData<String>()
    val displayPhoto: LiveData<String> get() = _displayPhoto

    private val _displayAuthorName = MutableLiveData<String>()
    val displayAuthorName: LiveData<String> get() = _displayAuthorName

    init {
        getPhoto(initParams.photoId)
    }

    private fun getPhoto(photoId: String){
        viewModelScope.launch {
            photoRepository.getPhotoDetails(photoId).collect {
                _displayPhoto.value = it.download_url
                _displayAuthorName.value = it.author
            }
        }
    }

    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(initParams: PhotoInitParams): PhotoDetailViewModel
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