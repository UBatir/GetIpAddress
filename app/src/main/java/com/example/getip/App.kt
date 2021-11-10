package com.example.getip

import android.app.Application
import com.example.getip.di.remoteModule
import com.example.getip.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        val modules = listOf(viewModelModule, remoteModule)
        GlobalContext.startKoin { // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@App)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(modules)
        }
    }
}