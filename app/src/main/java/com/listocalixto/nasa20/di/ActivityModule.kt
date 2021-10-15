package com.listocalixto.nasa20.di

import com.listocalixto.nasa20.domain.apod.ApodRepo
import com.listocalixto.nasa20.domain.apod.ApodRepoImpl
import com.listocalixto.nasa20.domain.asteroid.AsteroidRepo
import com.listocalixto.nasa20.domain.asteroid.AsteroidRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindAsteroidRepoImpl(repoImpl: AsteroidRepoImpl): AsteroidRepo

    @Binds
    abstract fun bindApodRepoImpl(repoImpl: ApodRepoImpl): ApodRepo

}