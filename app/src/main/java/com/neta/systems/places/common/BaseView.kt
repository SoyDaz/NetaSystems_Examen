package com.neta.systems.places.common

import android.app.Application
import android.content.Context

interface BaseView {
    fun getContext(): Context
    fun getApplication(): Application
}