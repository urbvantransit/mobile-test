package com.omargtz.mobiletest.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omargtz.mobiletest.data.LocationRepository
import com.omargtz.mobiletest.location.viewmodel.LocationViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val locationRepository: LocationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(LocationViewModel::class.java) ->
                    LocationViewModel(locationRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}