package com.zeltixdev.photoapp.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.zeltixdev.photoapp.repository.PhotoRepository
data class PhotoInitParams(val photoId: String)
class PhotoViewModel @AssistedInject constructor(
    photoRepository: PhotoRepository,
    @Assisted private val initParams: PhotoInitParams
): ViewModel(){
    val photo = photoRepository.getPhotoDetails(initParams.photoId).asLiveData()

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