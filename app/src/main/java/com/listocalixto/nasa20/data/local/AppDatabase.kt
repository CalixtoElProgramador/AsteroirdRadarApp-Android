package com.listocalixto.nasa20.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.listocalixto.nasa20.data.local.apod.ApodDao
import com.listocalixto.nasa20.data.local.asteroid.AsteroidDao
import com.listocalixto.nasa20.data.model.ApodEntity
import com.listocalixto.nasa20.data.model.AsteroidEntity

@Database(entities = [AsteroidEntity::class, ApodEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract val asteroidDao: AsteroidDao
    abstract val apodDao: ApodDao

}