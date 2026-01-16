package com.piyush.mytaxi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.piyush.mytaxi.navigation.NavGraph
import com.piyush.mytaxi.ui.theme.MyTaxiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTaxiTheme {
                NavGraph()
            }
        }
    }
}