package com.listocalixto.nasa20.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.listocalixto.nasa20.data.model.Asteroid
import com.listocalixto.nasa20.databinding.ListItemAsteroidBinding

class MainAdapter (val clickListener: OnClickListener) :
    ListAdapter<Asteroid, MainAdapter.AsteroidViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.bind(clickListener, asteroid)
    }

    class AsteroidViewHolder(private var binding: ListItemAsteroidBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: OnClickListener, asteroid: Asteroid) {
            binding.asteroid = asteroid
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AsteroidViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAsteroidBinding.inflate(layoutInflater, parent, false)

                return AsteroidViewHolder(binding)
            }
        }

    }
}

class DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.codename === newItem.codename
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }
}

class OnClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}
