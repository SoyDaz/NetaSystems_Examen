package com.neta.systems.places.injection.component

import com.neta.systems.places.common.BaseView
import com.neta.systems.places.injection.module.ContextModule
import com.neta.systems.places.injection.module.NetworkModule
import com.neta.systems.places.ui.placeshome.PlacesPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {

    fun inject(placesPresenter: PlacesPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule : NetworkModule): Builder
        fun contextModule(contextModule : ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}