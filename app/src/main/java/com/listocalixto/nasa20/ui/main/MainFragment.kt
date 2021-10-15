package com.listocalixto.nasa20.ui.main

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.work.*
import com.listocalixto.nasa20.R
import com.listocalixto.nasa20.databinding.FragmentMainBinding
import com.listocalixto.nasa20.presentation.apod.ApodViewModel
import com.listocalixto.nasa20.presentation.asteroid.AsteroidViewModel
import com.listocalixto.nasa20.ui.main.adapter.MainAdapter
import com.listocalixto.nasa20.ui.main.adapter.OnClickListener
import com.listocalixto.nasa20.work.RefreshDataWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val asteroidViewModel by activityViewModels<AsteroidViewModel>()
    private val apodViewModel by viewModels<ApodViewModel>()
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.lifecycleOwner = this
        binding.asteroidViewModel = asteroidViewModel
        binding.apodViewModel = apodViewModel

        asteroidViewModel.onMainNavigated()

        binding.recyclerView.adapter = MainAdapter(OnClickListener { asteroid ->
            asteroidViewModel.onAsteroidClicked(asteroid)
        })

        asteroidViewModel.navigateToDetails.observe(viewLifecycleOwner, { asteroid ->
            asteroid?.let {
                findNavController().navigate(MainFragmentDirections.toDetailsFragment())
                asteroidViewModel.onDetailsNavigated()
            }
        })

    }

}