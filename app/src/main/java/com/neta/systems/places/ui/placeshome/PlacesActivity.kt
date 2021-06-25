package com.neta.systems.places.ui.placeshome

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.neta.systems.places.R
import com.neta.systems.places.common.BaseActivity
import com.neta.systems.places.data.adapters.PlacesAdapter
import com.neta.systems.places.data.local.room.entity.WeatherEntity
import com.neta.systems.places.data.model.Post
import com.neta.systems.places.data.model.Weather
import com.neta.systems.places.data.model.WeatherResult
import com.neta.systems.places.databinding.ActivityPlacesBinding
import com.neta.systems.places.ui.maps.MapsFragment
import com.neta.systems.places.ui.other.PlacesFragment

class PlacesActivity : BaseActivity<PlacesPresenter>(), PlacesView {

    private lateinit var binding: ActivityPlacesBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val postAdapter = PlacesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_places)
        presenter.onViewCreated(application)
        binding.presenter = instantiatePresenter()
        linearLayoutManager = LinearLayoutManager(this)
        binding.posts.layoutManager = linearLayoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun onMapClick(view: View) {
        remplaceFragment(MapsFragment())
    }

    override fun onPlacesClick(view: View) {
        remplaceFragment(PlacesFragment())
    }

    override fun onConnectClick(view: View) {

    }

    fun remplaceFragment(f: Fragment) {
        val fManager = supportFragmentManager
        val fTransaction = fManager.beginTransaction()
        fTransaction.replace(R.id.fragmentContainer, f)
        fTransaction.commit()
    }

    override fun updatePlaces(posts: List<Post>) {
        binding.posts.adapter = postAdapter
        postAdapter.updatePosts(posts)
    }

    override fun updateWeather(resultApi: String) {
        var json: String = resultApi.replace("test(", "")
        json = json.removeRange(json.length - 1, json.length)
        val gson = Gson()
        val model: WeatherResult = gson.fromJson(json, WeatherResult::class.java)

        val bundle = Bundle()
        bundle.putParcelable("model", model)

        val mapsFragment = MapsFragment()
        mapsFragment.setArguments(bundle)

        remplaceFragment(mapsFragment)

        if(model.weather != null) {
            var mWeather: Weather = model.weather!!.get(0)
            var weatherEntity = WeatherEntity(mWeather.id!!,mWeather.main!!, mWeather.description!!)
            insertRoom(weatherEntity)
        }
    }

    private fun insertRoom(weather: WeatherEntity) {
        //presenter.room.insert(weather)
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