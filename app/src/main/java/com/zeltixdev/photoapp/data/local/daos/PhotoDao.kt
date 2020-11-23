package com.zeltixdev.photoapp.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zeltixdev.photoapp.models.Photo
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