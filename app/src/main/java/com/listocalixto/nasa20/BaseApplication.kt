package com.listocalixto.nasa20

import android.app.Application
import android.os.Build
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.listocalixto.nasa20.work.RefreshDataWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication: Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                setRequiresDeviceIdle(true)
            }.build()

        val request = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(request)
    }

}