package com.zeltixdev.photoapp.api

import com.zeltixdev.photoapp.models.Photo
import com.zeltixdev.photoapp.models.PhotoList
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper{
    override suspend fun getPhotos(): Response<PhotoList> = apiService.getPhotos()
    override suspend fun getPhotoDetails(photoId: Int): Response<Photo> = apiService.getPhotoDetail(photoId)
}