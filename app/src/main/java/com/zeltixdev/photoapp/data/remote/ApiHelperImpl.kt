package com.zeltixdev.photoapp.data.remote

import com.zeltixdev.photoapp.entity.Photo
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {
    override suspend fun getPhotos(): Response<List<Photo>> = apiService.getPhotos()
    override suspend fun getPhotoDetails(photoId: String): Response<Photo> = apiService.getPhotoDetail(photoId)
}