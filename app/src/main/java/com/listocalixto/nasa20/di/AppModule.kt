package com.listocalixto.nasa20.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.listocalixto.nasa20.BaseApplication
import com.listocalixto.nasa20.app.ApodRetrofitInstance
import com.listocalixto.nasa20.app.AsteroidRetrofitInstance
import com.listocalixto.nasa20.app.Constants.BASE_URL
import com.listocalixto.nasa20.data.local.AppDatabase
import com.listocalixto.nasa20.domain.apod.ApodWebService
import com.listocalixto.nasa20.domain.asteroid.AsteroidWebService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()

    @Singleton
    @Provides
    fun provideApodDao(db: AppDatabase) = db.apodDao

    @Singleton
    @Provides
    fun provideAsteroidDao(db: AppDatabase) = db.asteroidDao

    @Singleton
    @Provides
    fun provideMoshiInstance(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @ApodRetrofitInstance
    @Provides
    fun provideApodRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @AsteroidRetrofitInstance
    @Provides
    fun provideAsteroidRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApodWebService(@ApodRetrofitInstance retrofit: Retrofit): ApodWebService =
        retrofit.create(ApodWebService::class.java)

    @Singleton
    @Provides
    fun provideAsteroidWebService(@AsteroidRetrofitInstance retrofit: Retrofit): AsteroidWebService =
        retrofit.create(AsteroidWebService::class.java)

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication =
        app as BaseApplication

}