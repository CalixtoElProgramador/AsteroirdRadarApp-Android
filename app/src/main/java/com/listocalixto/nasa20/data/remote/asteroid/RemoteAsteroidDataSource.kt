package com.listocalixto.nasa20.data.remote.asteroid

import com.listocalixto.nasa20.app.Constants.API_KEY
import com.listocalixto.nasa20.data.model.Asteroid
import com.listocalixto.nasa20.data.model.AsteroidContainer
import com.listocalixto.nasa20.data.remote.parseAsteroidsJsonResult
import com.listocalixto.nasa20.domain.asteroid.AsteroidWebService
import org.json.JSONObject
import javax.inject.Inject

class RemoteAsteroidDataSource @Inject constructor(private val webService: AsteroidWebService) {

    suspend fun getAsteroids(startDate: String): AsteroidContainer =
        parseAsteroidsJsonResult(JSONObject(webService.getAsteroids(startDate, API_KEY)))

}