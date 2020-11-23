package com.zeltixdev.photoapp.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zeltixdev.photoapp.models.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    suspend fun getAllPhotos() : List<Photo>

    @Query("SELECT * FROM photos WHERE id = :id")
    suspend fun getPhoto(id: String): Photo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)
}