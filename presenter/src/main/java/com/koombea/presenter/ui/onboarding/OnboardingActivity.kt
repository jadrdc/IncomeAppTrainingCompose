package com.koombea.presenter.ui.onboarding

import android.content.Intent
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
import com.koombea.domain.model.UserModel
import com.koombea.presenter.ui.onboarding.navigation.OnboardingNavigation
import com.koombea.presenter.ui.onboarding.screen.CreateAccountScreen
import com.koombea.presenter.ui.onboarding.screen.LoginScreen
import com.koombea.presenter.ui.onboarding.screen.OnboardingScreen
import com.koombea.presenter.ui.onboarding.screen.SplashScreen
import com.koombea.presenter.ui.settings.ProfileActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingActivity : ComponentActivity() {

    private val viewModel: OnboardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidtemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Root(viewModel)
                }
            }
        }
    }

    @Composable
    fun Root(viewModel: OnboardingViewModel) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = OnboardingNavigation.Splash.route
        )
        {
            composable(OnboardingNavigation.Splash.route) {
                SplashScreen() {
                    navController.navigate(OnboardingNavigation.Onboarding.route)
                }
            }
            composable(OnboardingNavigation.Onboarding.route) {
                OnboardingScreen() {
                    navController.navigate(OnboardingNavigation.Login.route)
                }
            }
            composable(OnboardingNavigation.Login.route) {
                LoginScreen(
                    navController, viewModel
                ) { userModel ->
                    login(userModel)
                }
            }
            composable(OnboardingNavigation.SignUp.route) {
                CreateAccountScreen(
                    viewModel
                ) { userModel ->
                    login(userModel)
                }
            }
        }
    }

    private fun login(userModel: UserModel) {
        startActivity(
            Intent(
                this@OnboardingActivity,
                ProfileActivity::class.java
            ).apply {
                this.putExtra("user", userModel)
            })
        finish()
    }

}
