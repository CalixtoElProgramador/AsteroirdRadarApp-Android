package com.listocalixto.nasa20.ui.details

import android.os.Bundle
import android.system.Os.accept
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.listocalixto.nasa20.R
import com.listocalixto.nasa20.databinding.FragmentDetailsBinding
import com.listocalixto.nasa20.presentation.asteroid.AsteroidViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val asteroidViewModel by activityViewModels<AsteroidViewModel>()
    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = asteroidViewModel

        asteroidViewModel.showDialog.observe(viewLifecycleOwner, {
            if (it) {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(resources.getString(R.string.supporting_text))
                    .setOnDismissListener { asteroidViewModel.onHelpDialogClose() }
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                        asteroidViewModel.onHelpDialogClose()
                    }
                    .show()
            }
        })

        asteroidViewModel.navigationToList.observe(viewLifecycleOwner, {
            if (it) { activity?.onBackPressed(); asteroidViewModel.onListNavigated() }
        })

    }

}