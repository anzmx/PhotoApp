package com.zeltixdev.photoapp.data.remote

import com.zeltixdev.photoapp.entity.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v2/list")
    suspend fun getPhotos(): Response<List<Photo>>

    @GET("id/{id}/info")
    suspend fun getPhotoDetail(@Path("id") photoId: String): Response<Photo>
}