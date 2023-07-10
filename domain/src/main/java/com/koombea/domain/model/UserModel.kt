package com.koombea.domain.model

import android.os.Parcelable
import com.koombea.data.character.datasource.entity.Currency
import com.koombea.data.character.datasource.entity.Security
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val birthday: String = "",
    val currency: Currency = Currency.DOP,
    val security: Security = Security.BIOMETRIC
) : Parcelable