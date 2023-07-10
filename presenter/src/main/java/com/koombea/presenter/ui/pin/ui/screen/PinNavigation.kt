package com.koombea.presenter.ui.pin.ui.screen

sealed class PinNavigation(val route: String) {

    object Pin : PinNavigation("Pin")
    object ReenterPIN : PinNavigation("ReenterPIN")
}
