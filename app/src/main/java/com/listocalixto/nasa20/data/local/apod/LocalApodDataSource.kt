package com.listocalixto.nasa20.data.local.apod

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.listocalixto.nasa20.app.Constants
import com.listocalixto.nasa20.data.model.Apod
import com.listocalixto.nasa20.data.model.ApodEntity
import com.listocalixto.nasa20.data.model.asDomainModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LocalApodDataSource @Inject constructor(private val apodDao: ApodDao) {

    fun getPictureOfDay(): LiveData<Apod> {
        val today = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return Transformations.map(apodDao.getPictureOfDay(dateFormat.format(today.time))) { value ->
            value?.asDomainModel()
        }
    }

    fun insertAll(apodEntity: ApodEntity) = apodDao.insertAll(apodEntity)
}