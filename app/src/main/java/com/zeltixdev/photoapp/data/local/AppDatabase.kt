package com.zeltixdev.photoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zeltixdev.photoapp.data.local.daos.PhotoDao
import com.zeltixdev.photoapp.models.Photo

@Database(entities = [Photo::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPhotoDao(): PhotoDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
                instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
                Room.databaseBuilder(appContext, AppDatabase::class.java, "photos")
                        .fallbackToDestructiveMigration()
                        .build()
    }

}