package com.listocalixto.nasa20.domain.apod

import androidx.lifecycle.LiveData
import com.listocalixto.nasa20.data.model.Apod

interface ApodRepo {

    suspend fun refreshPictureOfDay()
    fun getPictureOfDay(): LiveData<Apod>

}