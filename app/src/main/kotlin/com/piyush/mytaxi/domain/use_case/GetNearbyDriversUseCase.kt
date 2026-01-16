package com.piyush.mytaxi.domain.use_case

import com.piyush.mytaxi.domain.repository.DriverRepository

class GetNearbyDriversUseCase(private val driverRepository: DriverRepository) {
    operator fun invoke() = driverRepository.getNearbyDrivers()
}