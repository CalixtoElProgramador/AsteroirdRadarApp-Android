package com.listocalixto.nasa20.domain.asteroid

import androidx.lifecycle.LiveData
import com.listocalixto.nasa20.app.Constants.API_QUERY_DATE_FORMAT
import com.listocalixto.nasa20.data.local.asteroid.LocalAsteroidDataSource
import com.listocalixto.nasa20.data.model.Asteroid
import com.listocalixto.nasa20.data.model.asDatabaseModel
import com.listocalixto.nasa20.data.remote.asteroid.RemoteAsteroidDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AsteroidRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteAsteroidDataSource,
    private val localDataSource: LocalAsteroidDataSource
): AsteroidRepo {

    override fun getAsteroids(): LiveData<List<Asteroid>> = localDataSource.getAsteroids()

    override suspend fun refreshAsteroids() =
        withContext(Dispatchers.IO) {
            val calendar = Calendar.getInstance()
            val currentTime = calendar.time
            val dateFormat = SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
            val asteroids = remoteDataSource.getAsteroids(dateFormat.format(currentTime))
            localDataSource.insertAll(*asteroids.asDatabaseModel())
        }
}