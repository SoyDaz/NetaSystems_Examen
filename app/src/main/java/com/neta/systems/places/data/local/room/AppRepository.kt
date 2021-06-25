package com.neta.systems.places.data.local.room

import com.neta.systems.places.data.local.room.entity.WeatherEntity
import io.reactivex.disposables.Disposable

interface AppRepository {

    fun insertWeather(weatherEntity: WeatherEntity): Disposable

}