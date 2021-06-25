package com.neta.systems.places.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

class AppPreferences: PreferencesHelper {

    private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_REQUEST_COUNT"

    private var mPrefs: SharedPreferences? = null
    private var context: Context? = null

    fun AppPreferences(c: Context) {
        context = c
        mPrefs = context!!.getSharedPreferences("APP", Context.MODE_PRIVATE)
    }

    override fun getCurrentRequestCount(): String? {
        return mPrefs!!.getString(PREF_KEY_CURRENT_USER_NAME, " ")
    }

    override fun setCurrentRequestCount(count: String) {
        mPrefs!!.edit().putString(PREF_KEY_CURRENT_USER_NAME, count).apply()
    }


}