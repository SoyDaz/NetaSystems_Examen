package com.neta.systems.places.injection.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.neta.systems.places.data.local.room.AppDataBase
import com.neta.systems.places.data.local.room.dao.WeatherDAO
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
@Suppress("unused")
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDataBase {
        return Room
                .databaseBuilder(application, AppDataBase::class.java, AppDataBase.DB_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build()
    }


    val roomCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }

    @Provides
    @Singleton
    fun provideDao(dataBase: AppDataBase): WeatherDAO {
        return dataBase.dao()
    }

}