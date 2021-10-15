package com.listocalixto.nasa20.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.listocalixto.nasa20.domain.apod.ApodRepoImpl
import com.listocalixto.nasa20.domain.asteroid.AsteroidRepoImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

@HiltWorker
class RefreshDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val apodRepo: ApodRepoImpl,
    private val asteroidRepo: AsteroidRepoImpl
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            apodRepo.refreshPictureOfDay()
            asteroidRepo.refreshAsteroids()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}