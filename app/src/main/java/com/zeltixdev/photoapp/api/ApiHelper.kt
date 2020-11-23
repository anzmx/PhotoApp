package com.zeltixdev.photoapp.api

import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.models.PhotoList
import retrofit2.Response

interface ApiHelper {
    suspend fun getPhotos():Response<List<Photo>>
    suspend fun getPhotoDetails(photoId:String):Response<Photo>
}