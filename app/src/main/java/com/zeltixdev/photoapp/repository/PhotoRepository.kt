package com.zeltixdev.photoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.google.android.material.snackbar.Snackbar
import com.zeltixdev.photoapp.api.ApiHelper
import com.zeltixdev.photoapp.data.local.daos.PhotoDao
import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.utilities.Resource
import com.zeltixdev.photoapp.utilities.networkBoundResource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val photoDao: PhotoDao
) {
     fun getPhotos() : Flow<Resource<List<Photo>>> {
        return networkBoundResource(
                query =  {photoDao.getAllPhotos()},
                fetch = {apiHelper.getPhotos()},
                saveFetchResult = {photos -> photoDao.insertAll(photos.body()!!)}
        )
    }

    fun getPhotoDetails(photoId:String): Flow<Photo> {
        return photoDao.getPhoto(photoId)
    }
}