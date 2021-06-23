package com.neta.systems.places.injection.module

import android.app.Application
import android.content.Context
import com.neta.systems.places.common.BaseView
import dagger.Module
import dagger.Provides

@Module
@Suppress("unused")
object ContextModule {

    @Provides
    internal fun provideContext(baseView: BaseView) : Context {
        return  baseView.getContext()
    }

    @Provides
    internal fun provideApplication(context: Context) : Application {
        return context.applicationContext as Application
    }
}