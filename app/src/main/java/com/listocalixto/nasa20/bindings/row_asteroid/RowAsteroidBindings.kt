package com.listocalixto.nasa20.bindings.row_asteroid

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.listocalixto.nasa20.R
import com.listocalixto.nasa20.data.model.Asteroid

class RowAsteroidBindings {

    companion object {

        @BindingAdapter("itemDescription")
        @JvmStatic
        fun MaterialCardView.setDescription(item: Asteroid?) {
            item?.let {
                val asteroidNameIs = resources.getString(R.string.card_asteroid_description_asteroid_name_is)
                val approachDateIs = resources.getString(R.string.card_asteroid_description_approach_date_is)
                val isHazardous = resources.getString(R.string.card_asteroid_description_is_hazardous)
                val nonHazardous = context.getString(R.string.card_asteroid_description_non_hazardous)
                contentDescription = if (it.isPotentiallyHazardous) {
                    "$asteroidNameIs ${it.codename}. $approachDateIs ${it.closeApproachDate}. $isHazardous"
                } else {
                    "$asteroidNameIs ${it.codename}. $approachDateIs ${it.closeApproachDate}. $nonHazardous"
                }

            }
        }

        @BindingAdapter("asteroidTitle")
        @JvmStatic
        fun TextView.setAsteroidTitle(item: Asteroid?) {
            item?.let {
                text = it.codename
            }
        }

        @BindingAdapter("asteroidSubtitle")
        @JvmStatic
        fun TextView.setAsteroidSubtitle(item: Asteroid?) {
            item?.let {
                text = it.closeApproachDate
            }
        }

        @BindingAdapter("asteroidImage")
        @JvmStatic
        fun ImageView.setAsteroidImage(item: Asteroid?) {
            item?.let {
                if (it.isPotentiallyHazardous) {
                    setImageResource(R.drawable.image_asteroid_hazardous)
                } else {
                    setImageResource(R.drawable.image_asteroid_safe)
                }
            }
        }

    }

}