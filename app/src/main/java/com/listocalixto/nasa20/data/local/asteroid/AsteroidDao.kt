package com.listocalixto.nasa20.data.local.asteroid

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.listocalixto.nasa20.data.model.AsteroidEntity

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroidentity WHERE closeApproachDate BETWEEN :startDate AND :endDate ORDER BY closeApproachDate ASC")
    fun getAsteroids(startDate: String, endDate: String): LiveData<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroidEntity: AsteroidEntity)

}