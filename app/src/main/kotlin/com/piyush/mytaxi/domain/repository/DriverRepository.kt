package com.piyush.mytaxi.domain.repository

import com.piyush.mytaxi.data.model.Driver
import kotlinx.coroutines.flow.Flow

interface DriverRepository {
    fun getNearbyDrivers(): Flow<List<Driver>>
}