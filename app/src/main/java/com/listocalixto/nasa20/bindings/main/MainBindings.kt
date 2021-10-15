package com.listocalixto.nasa20.bindings.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.appbar.AppBarLayout
import com.listocalixto.nasa20.R
import com.listocalixto.nasa20.app.Constants.MEDIA_TYPE_IMAGE
import com.listocalixto.nasa20.app.Constants.MEDIA_TYPE_VIDEO
import com.listocalixto.nasa20.data.model.Apod
import com.listocalixto.nasa20.data.model.Asteroid
import com.listocalixto.nasa20.ui.main.adapter.MainAdapter

class MainBindings {

    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun ImageView.setImageUrl(item: Apod?) {
            item?.let {
                when (it.mediaType) {
                    MEDIA_TYPE_IMAGE -> {
                        load(it.url) {
                            crossfade(600)
                        }
                    }
                    MEDIA_TYPE_VIDEO -> {
                    }
                    else -> {
                    }
                }
            }
        }

        @BindingAdapter("asteroidList")
        @JvmStatic
        fun RecyclerView.setList(items: List<Asteroid>?) {
            val adapter = adapter as MainAdapter
            adapter.submitList(items)
        }

        @BindingAdapter("appBarDescription")
        @JvmStatic
        fun AppBarLayout.setDescription(item: Apod?) {
            contentDescription = if (item == null) {
                resources.getString(R.string.appbar_layout_description_item_null)
            } else {
                "${resources.getString(R.string.appbar_layout_description_item_upload)} ${item.title}"
            }
        }

        @BindingAdapter("goneIfNotNull")
        @JvmStatic
        fun goneIfNotNull(view: View, it: Any?) {
            view.visibility = if (it != null) View.GONE else View.VISIBLE
        }

    }
}

