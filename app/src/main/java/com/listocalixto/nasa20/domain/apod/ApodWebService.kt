package com.listocalixto.nasa20.domain.apod

import com.listocalixto.nasa20.data.model.Apod
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodWebService {

    @GET("planetary/apod")
    suspend fun getPictureOfDay(
        @Query("api_key") apiKey: String
    ): Apod

}