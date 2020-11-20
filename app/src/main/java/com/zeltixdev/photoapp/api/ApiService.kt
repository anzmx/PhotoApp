package com.zeltixdev.photoapp.api

import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.models.PhotoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun getPhotos():Response<PhotoList>

    @GET("id/{id}/info")
    suspend fun getPhotoDetail(@Path("id") photoId:Int):Response<Photo>
}