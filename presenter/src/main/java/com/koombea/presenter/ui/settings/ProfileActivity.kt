package com.koombea.presenter.ui.settings

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
import com.koombea.presenter.ui.settings.navigation.ProfileNavigation
import com.koombea.androidtemplate.ui.theme.AndroidtemplateTheme
import com.koombea.domain.model.UserModel
import com.koombea.presenter.ui.settings.screen.EditProfileScreen
import com.koombea.presenter.ui.settings.screen.ProfileScreen
import com.koombea.presenter.ui.settings.screen.SettingsPickerScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : ComponentActivity() {

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.extras?.get("user") as UserModel?
        if (user != null) {
            viewModel.setUser(user)
        } else {
            viewModel.getUser()
        }

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
            startDestination = ProfileNavigation.Profile.route
        )
        {
            composable(ProfileNavigation.Profile.route) {
                ProfileScreen(
                    navController,
                    viewModel,
                )
            }
            composable(ProfileNavigation.EditProfile.route) {
                EditProfileScreen(
                    navController,
                    viewModel
                )
            }
            composable(ProfileNavigation.ChangeSettings.route + "/{" + ProfileNavigation.ChangeSettings.TITLE + "}") { backStackEntry ->
                SettingsPickerScreen(
                    backStackEntry.arguments?.getString(ProfileNavigation.ChangeSettings.TITLE)
                        ?: "",
                    navController, viewModel
                )
            }
        }
    }
}



