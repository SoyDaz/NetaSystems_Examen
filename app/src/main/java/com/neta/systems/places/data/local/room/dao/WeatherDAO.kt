package com.neta.systems.places.data.local.room.dao

import androidx.room.*
import com.neta.systems.places.data.local.room.entity.WeatherEntity

@Dao
interface WeatherDAO {

    @Insert
    fun insert(weatherEntity: WeatherEntity?)

    @Update
    fun update(weatherEntity: WeatherEntity?)

    @Delete
    fun delete(weatherEntity: WeatherEntity?)

    @Query("DELETE FROM weather_table")
    fun deleteAllWeatherTb()

    @Query("SELECT * FROM weather_table")
    fun getAll(): List<WeatherEntity>

}