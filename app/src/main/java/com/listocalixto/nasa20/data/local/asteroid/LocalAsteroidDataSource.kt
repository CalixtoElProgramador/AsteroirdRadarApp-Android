package com.listocalixto.nasa20.data.local.asteroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.listocalixto.nasa20.app.Constants
import com.listocalixto.nasa20.app.Constants.DEFAULT_END_DATE_DAYS
import com.listocalixto.nasa20.data.model.Asteroid
import com.listocalixto.nasa20.data.model.AsteroidEntity
import com.listocalixto.nasa20.data.model.asDomainModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LocalAsteroidDataSource @Inject constructor(private val asteroidDao: AsteroidDao) {

    fun getAsteroids(): LiveData<List<Asteroid>> {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, DEFAULT_END_DATE_DAYS) }
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        val startDateString = dateFormat.format(startDate.time)
        val endDateString = dateFormat.format(endDate.time)
        return Transformations.map(asteroidDao.getAsteroids(startDateString, endDateString)) {
            it.asDomainModel()
        }
    }

    fun insertAll(vararg asteroidEntity: AsteroidEntity) = asteroidDao.insertAll(*asteroidEntity)
}