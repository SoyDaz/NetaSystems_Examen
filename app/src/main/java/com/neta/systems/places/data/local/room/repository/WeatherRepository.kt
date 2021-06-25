package com.neta.systems.places.data.local.room.repository

import android.nfc.Tag
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.neta.systems.places.data.local.room.AppDataBase
import com.neta.systems.places.data.local.room.AppRepository
import com.neta.systems.places.data.local.room.dao.WeatherDAO
import com.neta.systems.places.data.local.room.entity.WeatherEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherRepository(val database: AppDataBase) : AppRepository {

    private val TAG = WeatherRepository::class.java.simpleName

    override fun insertWeather(weatherEntity: WeatherEntity): Disposable =
        Observable
                .fromCallable { database.dao().insert(weatherEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "food added: subscribe: $it")
                }

}