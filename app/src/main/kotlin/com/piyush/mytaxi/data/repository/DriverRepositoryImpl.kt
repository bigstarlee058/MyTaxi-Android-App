package com.piyush.mytaxi.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.piyush.mytaxi.data.model.Driver
import com.piyush.mytaxi.domain.repository.DriverRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DriverRepositoryImpl(
    private val firestore: FirebaseFirestore
) : DriverRepository {

    override fun getNearbyDrivers(): Flow<List<Driver>> = callbackFlow {
        val driversCollection = firestore.collection("drivers")
        val listener = driversCollection.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception)
                return@addSnapshotListener
            }
            val drivers = snapshot?.toObjects(Driver::class.java) ?: emptyList()
            trySend(drivers).isSuccess
        }
        awaitClose { listener.remove() }
    }
}