package com.neta.systems.places.ui.placeshome

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.neta.systems.places.R
import com.neta.systems.places.common.BaseActivity
import com.neta.systems.places.data.model.Post
import com.neta.systems.places.databinding.ActivityPlacesBinding

class PlacesActivity : BaseActivity<PlacesPresenter>(), PlacesView {

    private lateinit var binding: ActivityPlacesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_places)
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updatePlaces(posts: List<Post>) {
        val len = posts.size
        Log.i("TAG", "Elementos en la list $len")
    }

    override fun showError(error: String) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun instantiatePresenter(): PlacesPresenter {
        return PlacesPresenter(this)
    }
}