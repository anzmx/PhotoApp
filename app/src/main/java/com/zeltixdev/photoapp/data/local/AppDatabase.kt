package com.zeltixdev.photoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zeltixdev.photoapp.data.local.dao.PhotoDao
import com.zeltixdev.photoapp.entity.Photo

private const val DATABASE_NAME = "photos"

@Database(entities = [Photo::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPhotoDao(): PhotoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}