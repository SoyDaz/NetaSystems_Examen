package com.neta.systems.places.ui.placeshome

import android.view.View
import com.neta.systems.places.common.BaseView
import com.neta.systems.places.data.model.Post
import com.neta.systems.places.data.model.Weather

interface PlacesView : BaseView {
    fun onMapClick(view: View)
    fun onPlacesClick(view: View)
    fun onConnectClick(view: View)
    fun updatePlaces(posts: List<Post>)
    fun updateWeather(resultApi: String)
    fun showError(error: String)
    fun showLoading()
    fun hideLoading()
}