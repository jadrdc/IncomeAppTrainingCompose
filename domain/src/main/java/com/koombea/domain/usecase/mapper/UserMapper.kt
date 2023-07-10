package com.koombea.domain.usecase.mapper

import com.koombea.data.character.datasource.entity.User
import com.koombea.domain.model.UserModel

class UserMapper {
    companion object {
        fun map(data: User): UserModel {
            return data.let {
                UserModel(
                    name = it.name ?: "",
                    email = it.email ?: "",
                    currency = it.currency,
                    security = it.security,
                    birthday = ""
                )
            }
        }

        fun map(data: UserModel): User {
            return data.let {
                User(
                    id = "1",
                    name = it.name ?: "",
                    email = it.email ?: "",
                    currency = it.currency,
                    security = it.security,
                )
            }
        }
    }
}