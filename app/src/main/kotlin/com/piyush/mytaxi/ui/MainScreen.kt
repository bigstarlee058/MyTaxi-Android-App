package com.piyush.mytaxi.ui

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.piyush.mytaxi.viewmodel.MainUiState
import com.piyush.mytaxi.viewmodel.MainViewModel
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.MapView
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    if (locationPermissionsState.allPermissionsGranted) {
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getRideData()
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when (uiState) {
                is MainUiState.Success -> {
                    val successState = uiState as MainUiState.Success
                    val userLocation = successState.location
                    val drivers = successState.drivers

                    val userGeoPoint = GeoPoint(userLocation.latitude, userLocation.longitude)

                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { context ->
                            MapView(context).apply {
                                setMultiTouchControls(true)
                                controller.setZoom(15.0)
                                controller.setCenter(userGeoPoint)

                                val userMarker = Marker(this)
                                userMarker.position = userGeoPoint
                                userMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                                userMarker.title = "You are here"
                                overlays.add(userMarker)

                                drivers.forEach { driver ->
                                    driver.location?.let {
                                        val driverGeoPoint = GeoPoint(it.latitude, it.longitude)
                                        val driverMarker = Marker(this)
                                        driverMarker.position = driverGeoPoint
                                        driverMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                                        driverMarker.title = driver.name
                                        overlays.add(driverMarker)
                                    }
                                }
                            }
                        }
                    )
                }
                else -> {}
            }
        }
    } else {
        LaunchedEffect(Unit) {
            locationPermissionsState.launchMultiplePermissionRequest()
        }
    }
}