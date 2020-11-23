package com.zeltixdev.photoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.google.android.material.snackbar.Snackbar
import com.zeltixdev.photoapp.api.ApiHelper
import com.zeltixdev.photoapp.data.local.daos.PhotoDao
import com.zeltixdev.photoapp.models.Photo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val photoDao: PhotoDao
) {
    suspend fun getPhotos() : List<Photo>{
            apiHelper.getPhotos().let {
                if (it.isSuccessful){
                    photoDao.insertAll(it.body()!!)
                }
            }
        return photoDao.getAllPhotos()
    }

    suspend fun getPhotoDetails(photoId:String): Photo{
            apiHelper.getPhotoDetails(photoId).let {
                if (it.isSuccessful){
                    photoDao.insert(it.body()!!)
                }
            }
        return photoDao.getPhoto(photoId)
    }
}