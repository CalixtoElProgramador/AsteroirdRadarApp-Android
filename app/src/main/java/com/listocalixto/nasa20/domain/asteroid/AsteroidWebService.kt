package com.listocalixto.nasa20.domain.asteroid

import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidWebService {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("api_key") apiKey: String
    ): String
}