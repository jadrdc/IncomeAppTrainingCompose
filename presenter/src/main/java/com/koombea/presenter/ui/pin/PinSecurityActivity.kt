package com.koombea.presenter.ui.pin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.koombea.androidtemplate.ui.theme.AndroidtemplateTheme
import com.koombea.presenter.ui.pin.ui.screen.PinNavigation
import com.koombea.presenter.ui.pin.ui.screen.PinScreen

class PinSecurityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidtemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Root()
                }
            }
        }
    }

    @Composable
    fun Root() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = PinNavigation.Pin.route
        ) {
            composable(PinNavigation.Pin.route) {
                PinScreen("Let's setup your pin") {
                    navController.navigate(PinNavigation.ReenterPIN.route)
                }
            }
            composable(PinNavigation.ReenterPIN.route) {
                PinScreen("Ok. Re type your PIN again.") { finish() }
            }
        }
    }
}
