package com.koombea.data.character.datasource.entity

enum class Currency(val currency: String) {
    USD("United States (USD)"),
    JPY("Japan (JPY)"),
    EUR("Germany (EUR)"),
    COP("Colombia (COP)"),
    DOP("Dominican Republic DOP"),
    SYSTEM_PREFERENCE("System Preference"),
}

fun getCurrencyByName(currency: String): Currency {
    return Currency.values().first { it.name == currency }
}