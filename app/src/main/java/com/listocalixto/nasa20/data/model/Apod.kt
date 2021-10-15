package com.listocalixto.nasa20.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class Apod(
    val date: String,
    @Json(name = "media_type")
    val mediaType: String,
    val title: String,
    val url: String
)

@Entity
data class ApodEntity(
    @PrimaryKey val date: String,
    val mediaType: String,
    val title: String,
    val url: String

)

fun ApodEntity.asDomainModel(): Apod = Apod(
    this.date,
    this.mediaType,
    this.title,
    this.url
)

fun Apod.asDatabaseModel(): ApodEntity = ApodEntity(
    this.date,
    this.mediaType,
    this.title,
    this.url
)
