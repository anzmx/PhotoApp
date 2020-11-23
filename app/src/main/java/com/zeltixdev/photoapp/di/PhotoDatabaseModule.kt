package com.zeltixdev.photoapp.di

import android.app.Application
import com.zeltixdev.photoapp.BuildConfig
import com.zeltixdev.photoapp.api.ApiHelper
import com.zeltixdev.photoapp.api.ApiHelperImpl
import com.zeltixdev.photoapp.api.ApiService
import com.zeltixdev.photoapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PhotoDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun providePhotoDao(database: AppDatabase) = database.getPhotoDao()

}