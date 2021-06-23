package com.neta.systems.places.ui.placeshome

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neta.systems.places.R
import com.neta.systems.places.common.BaseActivity
import com.neta.systems.places.data.adapters.PlacesAdapter
import com.neta.systems.places.data.model.Post
import com.neta.systems.places.databinding.ActivityPlacesBinding

class PlacesActivity : BaseActivity<PlacesPresenter>(), PlacesView {

    private lateinit var binding: ActivityPlacesBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val postAdapter = PlacesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_places)
        binding = ActivityPlacesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linearLayoutManager = LinearLayoutManager(this)
        binding.posts.layoutManager = linearLayoutManager
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updatePlaces(posts: List<Post>) {
        val len = posts.size
        Log.i("TAG", "Elementos en la list $len")
        binding.posts.adapter = postAdapter
        postAdapter.updatePosts(posts)
    }

    override fun showError(error: String) {

    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): PlacesPresenter {
        return PlacesPresenter(this)
    }
}