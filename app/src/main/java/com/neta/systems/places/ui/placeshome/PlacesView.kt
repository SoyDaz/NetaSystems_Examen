package com.neta.systems.places.ui.placeshome

import com.neta.systems.places.common.BaseView
import com.neta.systems.places.data.model.Post

interface PlacesView : BaseView {
    fun updatePlaces(posts: List<Post>)
    fun showError(error: String)
    fun showLoading()
    fun hideLoading()
}