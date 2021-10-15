package com.listocalixto.nasa20.domain.asteroid

import androidx.lifecycle.LiveData
import com.listocalixto.nasa20.data.model.Asteroid

interface AsteroidRepo {

    suspend fun refreshAsteroids()
    fun getAsteroids(): LiveData<List<Asteroid>>

}