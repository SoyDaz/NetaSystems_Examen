package com.neta.systems.places.ui.placeshome

import com.neta.systems.places.R
import com.neta.systems.places.api.PlacesApi
import com.neta.systems.places.common.BasePresenter
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PlacesPresenter (placesView: PlacesView) : BasePresenter<PlacesView>(placesView) {
    @Inject
    lateinit var api: PlacesApi

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadPlaces()
    }

    fun loadPlaces() {
        subscription = api
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate{}
                .subscribe({ postList -> view.updatePlaces(postList) })
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}