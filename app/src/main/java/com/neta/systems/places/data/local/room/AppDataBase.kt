package com.neta.systems.places.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neta.systems.places.data.local.room.dao.WeatherDAO
import com.neta.systems.places.data.local.room.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = AppDataBase.VERSION)
abstract class AppDataBase: RoomDatabase() {

    companion object {
        const val DB_NAME = "database-app"
        const val VERSION = 1
    }

    abstract fun dao(): WeatherDAO

}