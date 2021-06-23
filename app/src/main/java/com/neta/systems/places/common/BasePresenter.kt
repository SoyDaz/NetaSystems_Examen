package com.neta.systems.places.common

import com.neta.systems.places.injection.component.DaggerPresenterInjector
import com.neta.systems.places.injection.component.PresenterInjector
import com.neta.systems.places.injection.module.ContextModule
import com.neta.systems.places.injection.module.NetworkModule
import com.neta.systems.places.ui.placeshome.PlacesPresenter

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {

        when (this) {
            is PlacesPresenter -> injector.inject(this)
        }

    }
}