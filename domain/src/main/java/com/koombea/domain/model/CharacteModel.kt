package com.koombea.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CharacterModel(val id: Int,
                         val name: String,
                         val image: String ): Parcelable