package com.zeltixdev.photoapp.repository

import com.zeltixdev.photoapp.entity.Photo
import com.zeltixdev.photoapp.networking.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(): Flow<Resource<List<Photo>>>
    fun getPhotoDetails(photoId: String): Flow<Photo>
}