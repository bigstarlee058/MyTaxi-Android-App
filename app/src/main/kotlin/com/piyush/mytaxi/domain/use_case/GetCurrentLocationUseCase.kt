package com.piyush.mytaxi.domain.use_case

import com.piyush.mytaxi.domain.repository.LocationRepository

class GetCurrentLocationUseCase(private val locationRepository: LocationRepository) {
    operator fun invoke() = locationRepository.getCurrentLocation()
}