package com.listocalixto.nasa20.domain.apod

import androidx.lifecycle.LiveData
import com.listocalixto.nasa20.data.local.apod.LocalApodDataSource
import com.listocalixto.nasa20.data.model.Apod
import com.listocalixto.nasa20.data.model.asDatabaseModel
import com.listocalixto.nasa20.data.remote.apod.RemoteApodDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApodRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteApodDataSource,
    private val localDataSource: LocalApodDataSource
) : ApodRepo {

    override fun getPictureOfDay(): LiveData<Apod> = localDataSource.getPictureOfDay()

    override suspend fun refreshPictureOfDay() {
        withContext(Dispatchers.IO) {
            localDataSource.insertAll(remoteDataSource.getPictureOfDay().asDatabaseModel())
        }
    }
}