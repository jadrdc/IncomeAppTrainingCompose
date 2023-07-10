package com.koombea.presenter.ui.settings.navigation

sealed class ProfileNavigation(val route: String) {
    object Profile : ProfileNavigation("Profile")
    object EditProfile : ProfileNavigation("EditProfile")
    object ChangeSettings : ProfileNavigation("ChangeSettings") {
        const val TITLE = "TITLE"
    }
}