package com.koombea.presenter.ui.onboarding.navigation

sealed class OnboardingNavigation(val route: String) {
    object Splash : OnboardingNavigation("Splash")
    object Login : OnboardingNavigation("Login")
    object SignUp : OnboardingNavigation("SignUp")
    object Onboarding : OnboardingNavigation("Onboarding")
}