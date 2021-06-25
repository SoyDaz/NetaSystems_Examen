package com.neta.systems.places.injection.module

import com.neta.systems.places.data.local.prefs.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
@Suppress("unused")
object PreferencesModule {

    @Provides
    @Reusable
    internal fun provideAppPreferences(): AppPreferences {
        return AppPreferences()
    }

}