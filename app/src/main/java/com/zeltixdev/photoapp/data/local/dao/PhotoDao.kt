package com.zeltixdev.photoapp.data.local.dao

import androidx.room.*
import com.zeltixdev.photoapp.entity.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    fun getAllPhotos() : Flow<List<Photo>>

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhoto(id: String): Flow<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)
}