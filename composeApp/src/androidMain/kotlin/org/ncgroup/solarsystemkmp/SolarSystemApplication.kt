package org.ncgroup.solarsystemkmp

import android.app.Application

class SolarSystemApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
    companion object {
        lateinit var INSTANCE: SolarSystemApplication
    }
}