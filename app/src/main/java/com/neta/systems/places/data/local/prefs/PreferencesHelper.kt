package com.neta.systems.places.data.local.prefs

interface PreferencesHelper {

    fun getCurrentRequestCount(): String?
    fun setCurrentRequestCount(count: String)

}