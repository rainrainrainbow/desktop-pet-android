package com.deskpet

import android.app.Application

class PetApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: PetApplication
            private set
    }
}
