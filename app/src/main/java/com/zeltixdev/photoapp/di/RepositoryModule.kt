package com.zeltixdev.photoapp.di

import com.zeltixdev.photoapp.repository.PhotoRepository
import com.zeltixdev.photoapp.repository.impl.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePhotoRepository(repository: PhotoRepositoryImpl): PhotoRepository = repository
}