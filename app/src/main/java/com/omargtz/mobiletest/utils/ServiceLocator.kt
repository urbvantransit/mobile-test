package com.omargtz.mobiletest.utils

import androidx.annotation.VisibleForTesting
import com.omargtz.mobiletest.data.LocationRepository
import com.omargtz.mobiletest.data.LocationRepositoryImp
import com.omargtz.mobiletest.data.remote.firebase.FirebaseDbDataSource
import com.omargtz.mobiletest.data.remote.firebase.FirebaseDbDataSourceImp
import com.omargtz.mobiletest.data.remote.firebase.FirebaseService
import com.omargtz.mobiletest.data.remote.geocoding.GeocodingDataSource
import com.omargtz.mobiletest.data.remote.geocoding.GeocodingDataSourceImp
import com.omargtz.mobiletest.data.remote.geocoding.GeocodingService

object ServiceLocator{

    @Volatile
    var locationRepository: LocationRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(): LocationRepository{
        synchronized(this) {
            return locationRepository ?: locationRepository ?: createLocationRepository()
        }
    }


    fun createLocationRepository():LocationRepository{
        return LocationRepositoryImp(createGeocodingDataSource(), createFirebaseDataSource())
    }


    fun createGeocodingDataSource(): GeocodingDataSource{
        return GeocodingDataSourceImp(GeocodingService.geocodingServiceProvider)
    }


    fun createFirebaseDataSource():FirebaseDbDataSource{
        return FirebaseDbDataSourceImp(FirebaseService.dbReference)
    }

}


