package com.listocalixto.nasa20.data.local.apod

import androidx.lifecycle.LiveData
import androidx.room.*
import com.listocalixto.nasa20.data.model.ApodEntity

@Dao
interface ApodDao {

    @Query("SELECT * FROM ApodEntity WHERE date LIKE :today")
    fun getPictureOfDay(today: String): LiveData<ApodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(apodEntity: ApodEntity)


}