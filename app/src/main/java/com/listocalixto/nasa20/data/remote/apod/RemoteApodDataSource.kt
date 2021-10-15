package com.listocalixto.nasa20.data.remote.apod

import com.listocalixto.nasa20.app.Constants.API_KEY
import com.listocalixto.nasa20.data.model.Apod
import com.listocalixto.nasa20.domain.apod.ApodWebService
import javax.inject.Inject

class RemoteApodDataSource @Inject constructor(private val webService: ApodWebService) {

    suspend fun getPictureOfDay(): Apod = webService.getPictureOfDay(API_KEY)

}