package com.neta.systems.places.injection.component

import com.neta.systems.places.common.BaseView
import com.neta.systems.places.injection.module.PreferencesModule
import com.neta.systems.places.injection.module.ContextModule
import com.neta.systems.places.injection.module.NetworkModule
import com.neta.systems.places.injection.module.RoomModule
import com.neta.systems.places.ui.placeshome.PlacesActivity
import com.neta.systems.places.ui.placeshome.PlacesPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

//, (RoomModule::class)
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class), (PreferencesModule::class), (RoomModule::class)])
interface PresenterInjector {

    fun inject(placesPresenter: PlacesPresenter)
    fun inject(placesActivity: PlacesActivity)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun preferencesModule(preferencesModule: PreferencesModule): Builder
        fun roomModule(roomModule: RoomModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}