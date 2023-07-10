package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.entity.User
import com.koombea.data.character.repository.UserRepository
import com.koombea.domain.model.UserModel
import com.koombea.domain.usecase.mapper.UserMapper

class GetUserCase(private val userRepository: UserRepository) {
    suspend fun perform(): OperationResult<UserModel> {
        return when (val result = userRepository.getUser()) {
            is OperationResult.Success -> {
                OperationResult.Success(result.data?.let { UserMapper.map(it) })
            }

            is OperationResult.Error -> {
                OperationResult.Error(Exception())
            }
        }
    }

    suspend fun saveUser() {
        userRepository.saveUser()
    }

    suspend fun updateUser(user: User) {
        userRepository.updateUser(user)
    }
}