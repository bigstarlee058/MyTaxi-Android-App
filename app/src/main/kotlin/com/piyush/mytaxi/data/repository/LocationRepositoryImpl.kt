package com.piyush.mytaxi.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.piyush.mytaxi.domain.repository.LocationRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocationRepositoryImpl(
    private val context: Context
) : LocationRepository {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Flow<Location> = callbackFlow {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let { trySend(it).isSuccess }
        }
        // This is a simple implementation that only gets the last known location.
        // A more robust implementation would request location updates.
        awaitClose { /* Unregister location updates here if you were requesting them */ }
    }
}