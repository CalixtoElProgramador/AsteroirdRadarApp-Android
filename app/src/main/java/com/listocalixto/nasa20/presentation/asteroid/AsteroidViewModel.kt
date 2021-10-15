package com.listocalixto.nasa20.presentation.asteroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.listocalixto.nasa20.data.model.Asteroid
import com.listocalixto.nasa20.domain.asteroid.AsteroidRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AsteroidViewModel @Inject constructor(private val repo: AsteroidRepo) : ViewModel() {

    private val _navigateToDetails = MutableLiveData<Long?>()
    val navigateToDetails: LiveData<Long?>
        get() = _navigateToDetails

    private val _navigationToList = MutableLiveData<Boolean>()
    val navigationToList: LiveData<Boolean>
        get() = _navigationToList

    private val _asteroidClicked = MutableLiveData<Asteroid?>()
    val asteroidClicked: LiveData<Asteroid?>
        get() = _asteroidClicked

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean>
        get() = _showDialog

    val asteroids = repo.getAsteroids()

    init {
        _showDialog.value = false
        _navigationToList.value = false
        refreshAsteroids()
    }

    private fun refreshAsteroids() {
        viewModelScope.launch {
            try {
                repo.refreshAsteroids()
            } catch (e: Exception) {

            }

        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToDetails.value = asteroid.id
        _asteroidClicked.value = asteroid
    }

    fun onDetailsNavigated() {
        _navigateToDetails.value = null
    }

    fun onMainNavigated() {
        _asteroidClicked.value = null
    }

    fun onHelpDialogClick() {
        _showDialog.value = true
    }

    fun onHelpDialogClose() {
        _showDialog.value = false
    }

    fun onBackArrowClick() {
        _navigationToList.value = true
    }

    fun onListNavigated() {
        _navigationToList.value = false
    }

}