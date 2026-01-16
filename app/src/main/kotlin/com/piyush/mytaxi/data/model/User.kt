package com.piyush.mytaxi.data.model

import com.google.firebase.Timestamp

enum class UserRole {
    RIDER,
    DRIVER
}

data class User(
    val uid: String = "",
    val phoneNumber: String = "",
    val role: UserRole = UserRole.RIDER,
    val createdAt: Timestamp = Timestamp.now()
)