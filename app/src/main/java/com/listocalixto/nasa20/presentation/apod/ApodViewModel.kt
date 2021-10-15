package com.listocalixto.nasa20.presentation.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.nasa20.domain.apod.ApodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodViewModel @Inject constructor(private val repo: ApodRepo) : ViewModel() {

    val pictureOfDay = repo.getPictureOfDay()

    init {
        refreshPictureOfDay()
    }

    private fun refreshPictureOfDay() {
        viewModelScope.launch {
            try {
                repo.refreshPictureOfDay()
            } catch (e: Exception) {

            }
        }
    }

}