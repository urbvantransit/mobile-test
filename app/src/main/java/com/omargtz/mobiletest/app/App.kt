package com.omargtz.mobiletest.app

import android.app.Application
import com.omargtz.mobiletest.data.LocationRepository
import com.omargtz.mobiletest.utils.ServiceLocator

class App: Application(){


    val LocationRepository: LocationRepository
    get() = ServiceLocator.provideTasksRepository()

    override fun onCreate() {
        super.onCreate()
    }


}



