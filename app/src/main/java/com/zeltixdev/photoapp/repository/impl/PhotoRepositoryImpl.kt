package com.zeltixdev.photoapp.repository.impl

import com.zeltixdev.photoapp.data.local.dao.PhotoDao
import com.zeltixdev.photoapp.data.remote.ApiHelper
import com.zeltixdev.photoapp.entity.Photo
import com.zeltixdev.photoapp.repository.PhotoRepository
import com.zeltixdev.photoapp.networking.Resource
import com.zeltixdev.photoapp.networking.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val photoDao: PhotoDao
) : PhotoRepository {

    override fun getPhotos(): Flow<Resource<List<Photo>>> {
        return networkBoundResource(
            query = { photoDao.getAllPhotos() },
            fetch = { apiHelper.getPhotos() },
            saveFetchResult = { photos -> photoDao.insertAll(photos.body()!!) }
        )
    }

    override fun getPhotoDetails(photoId: String): Flow<Photo> = photoDao.getPhoto(photoId)
}