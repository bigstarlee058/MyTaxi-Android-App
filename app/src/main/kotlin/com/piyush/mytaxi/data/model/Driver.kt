package com.piyush.mytaxi.data.model

import com.google.firebase.firestore.GeoPoint

data class Driver(
    val uid: String = "",
    val name: String = "",
    val location: GeoPoint? = null
)