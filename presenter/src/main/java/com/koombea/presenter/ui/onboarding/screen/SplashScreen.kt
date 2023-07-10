package com.koombea.presenter.ui.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.koombea.presenter.R
import com.koombea.presenter.ui.theme.PURPLE
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(goToOnboarding: () -> Unit) {

    Box(
        Modifier
            .fillMaxSize()
            .background(PURPLE)
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "splash",
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        )
    }
    LaunchedEffect(true) {
        delay(2000L)
        goToOnboarding()
    }
}