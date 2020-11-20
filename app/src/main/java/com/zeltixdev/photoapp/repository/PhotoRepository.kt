package com.zeltixdev.photoapp.repository

import com.zeltixdev.photoapp.api.ApiHelper
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getPhotos() = apiHelper.getPhotos()
    suspend fun getPhotoDetails(photoId:Int) = apiHelper.getPhotoDetails(photoId)
}