package com.piyush.mytaxi.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyush.mytaxi.data.model.Driver
import com.piyush.mytaxi.domain.use_case.GetCurrentLocationUseCase
import com.piyush.mytaxi.domain.use_case.GetNearbyDriversUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MainUiState {
    object Idle : MainUiState()
    object Loading : MainUiState()
    data class Success(val location: Location, val drivers: List<Driver>) : MainUiState()
    data class Error(val message: String) : MainUiState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getNearbyDriversUseCase: GetNearbyDriversUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun getRideData() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            try {
                getCurrentLocationUseCase().collect { location ->
                    getNearbyDriversUseCase().collect { drivers ->
                        _uiState.value = MainUiState.Success(location, drivers)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error(e.message ?: "Failed to get ride data")
            }
        }
    }
}