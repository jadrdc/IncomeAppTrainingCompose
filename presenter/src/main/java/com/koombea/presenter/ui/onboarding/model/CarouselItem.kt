package com.koombea.presenter.ui.onboarding.model

import com.koombea.presenter.R

class CarouselElement(val pict: Int, val title: String, val subtitle: String) {

    companion object {
        fun getCarouselList(): List<CarouselElement> {

            return listOf<CarouselElement>(
                CarouselElement(
                    R.drawable.cash,
                    "Gain total control \n" +
                            "of your money",
                    "Become your own money manager and make every cent count",
                ),

                CarouselElement(
                    R.drawable.money,
                    "Know where your money goes", "Track your transaction easily,\n" +
                            "with categories and financial report "
                ),

                CarouselElement(
                    R.drawable.plan,
                    "Planning ahead", "Setup your budget for each category\n" +
                            "so you in control"
                )
            )
        }
    }
}
