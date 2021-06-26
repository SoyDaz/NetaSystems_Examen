package com.neta.systems.places.ui.placeshome

import android.app.Application
import android.view.View
import com.neta.systems.places.api.ApiServiceInterface
import com.neta.systems.places.common.BasePresenter
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PlacesPresenter (placesView: PlacesView) : BasePresenter<PlacesView>(placesView) {

    @Inject
    lateinit var apiServiceInterface: ApiServiceInterface

    private var subscription: Disposable? = null

    fun onClickMaps(v: View) {
        view.onMapClick(v)
    }

    fun onClickPlaces(v: View) {
        view.onPlacesClick(v)
    }

    fun onClickConnect(v: View) {
        getWeatherApi()
    }

    override fun onViewCreated(application: Application) {
        view.hideLoading()
    }

    fun loadPlaces() {
        view.showLoading()
        subscription = apiServiceInterface
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate{ view.hideLoading() }
                .subscribe({ postList -> view.updatePlaces(postList) })
    }

    fun getWeatherApi() {
        view.showLoading()
        subscription = apiServiceInterface
            .getWeatherMarker()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate{ view.hideLoading() }
            .subscribe({ resultAPI -> view.updateWeather(resultAPI) })

    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}