package com.zeltixdev.photoapp.api

import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.models.PhotoList
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper{
    override suspend fun getPhotos(): Response<List<Photo>> = apiService.getPhotos()
    override suspend fun getPhotoDetails(photoId: String): Response<Photo> = apiService.getPhotoDetail(photoId)
}