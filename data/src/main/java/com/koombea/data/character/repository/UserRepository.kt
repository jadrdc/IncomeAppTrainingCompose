package com.koombea.data.character.repository

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.entity.User

interface UserRepository {

    suspend fun getUser(): OperationResult<User>
    suspend fun saveUser()
    suspend fun updateUser(updateUser: User)
    suspend fun findUser(user: String, password: String): OperationResult<User>
    suspend fun saveUser(user: User): OperationResult<Boolean>
}