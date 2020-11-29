package com.zeltixdev.photoapp.data.remote

import com.zeltixdev.photoapp.entity.Photo
import retrofit2.Response

interface ApiHelper {
    suspend fun getPhotos():Response<List<Photo>>
    suspend fun getPhotoDetails(photoId:String):Response<Photo>
}