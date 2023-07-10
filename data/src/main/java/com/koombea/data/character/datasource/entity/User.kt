package com.koombea.data.character.datasource.entity

data class User(
    val name: String,
    val email: String,
    val password: String = "jadrdc",
    val birthday: String = "tetet",
    val id: String = "1",
    val currency: Currency = Currency.DOP,
    val security: Security = Security.BIOMETRIC
)