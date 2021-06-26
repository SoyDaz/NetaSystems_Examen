package com.neta.systems.places.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.util.CollectionUtils
import com.neta.systems.places.R
import com.neta.systems.places.data.local.sqlite.RegisterWether
import com.neta.systems.places.databinding.ItemPlaceBinding

class PlacesAdapter(private val activity: FragmentActivity?, val items: List<RegisterWether>): RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    private var posts: List<RegisterWether> = items

    class PlacesViewHolder(private val binding: ItemPlaceBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (post: RegisterWether) {
            binding.post = post
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val layoutInflater = LayoutInflater.from(activity)
        val binding: ItemPlaceBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_place, parent, false)
        return PlacesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder?.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun updatePosts(posts: List<RegisterWether>) {
        this.posts = posts
        notifyDataSetChanged()
    }

}