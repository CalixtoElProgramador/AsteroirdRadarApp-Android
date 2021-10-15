package com.listocalixto.nasa20.bindings.details

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.listocalixto.nasa20.R
import com.listocalixto.nasa20.data.model.Asteroid
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsBindings {

    companion object {

        @BindingAdapter("imageDetailsDescription")
        @JvmStatic
        fun ImageView.setDescription(item: Asteroid?) {
            item?.let {
                contentDescription = if (it.isPotentiallyHazardous) {
                    resources.getString(R.string.image_asteroid_details_description_is_hazardous)
                } else {
                    context.getString(R.string.image_asteroid_details_description_non_hazardous)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("setValuesWithUnits")
        @JvmStatic
        fun TextView.setUnits(item: Asteroid?) {
            item?.let {
                text = when(id) {
                    R.id.asteroidAbsoluteMagnitude -> {
                        "${it.absoluteMagnitude} au"
                    }
                    R.id.asteroidEstimatedDiameter -> {
                        "${it.distanceFromEarth} km"
                    }
                    R.id.asteroidRelativeVelocity -> {
                        "${it.relativeVelocity} km/s"
                    }
                    else -> {
                        "${it.distanceFromEarth} au"
                    }
                }
            }
        }

    }

}