package com.neta.systems.places.data.local.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.neta.systems.places.data.local.room.entity.WeatherEntity

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "api.db"
        private val TB_NAME = "weather_tb"
        private val TB_COLUMN_ID = "weather_column_id"
        private val TB_COLUMN_MAIN = "weather_column_main"
        private val TB_COLUMN_DESCRIPTION = "weather_column_description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TB_QUERY: String = ("CREATE TABLE $TB_NAME ($TB_COLUMN_ID  INTEGER Primary key AUTOINCREMENT ,$TB_COLUMN_MAIN  TEXT, $TB_COLUMN_DESCRIPTION TEXT) ")
        db!!.execSQL(CREATE_TB_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TB_NAME")
        onCreate(db)
    }

    val allWethers: List<RegisterWether>
        get() {
            val listWethers = ArrayList<RegisterWether>()
            val selectQuery = "SELECT * FROM $TB_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val whether = RegisterWether(
                            cursor.getInt(cursor.getColumnIndex(TB_COLUMN_ID)),
                            cursor.getString(cursor.getColumnIndex(TB_COLUMN_MAIN)),
                            cursor.getString(cursor.getColumnIndex(TB_COLUMN_DESCRIPTION)))
                    listWethers.add(whether)
                } while (cursor.moveToNext())
            }
            db.close()
            return listWethers
        }

    fun addWether(paramWhether: RegisterWether) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TB_COLUMN_MAIN, paramWhether.main)
        values.put(TB_COLUMN_DESCRIPTION, paramWhether.description)
        db.insert(TB_NAME, null, values)
        db.close()
    }

}