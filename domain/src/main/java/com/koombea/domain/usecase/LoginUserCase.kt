package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.entity.User
import com.koombea.data.character.repository.UserRepository
import com.koombea.domain.model.UserModel
import com.koombea.domain.usecase.mapper.UserMapper

class LoginUserCase(private val userRepository: UserRepository) {
    suspend fun loginUser(email: String, password: String): OperationResult<UserModel> {
        return when (val result = userRepository.findUser(email, password)) {
            is OperationResult.Success -> {
                OperationResult.Success(result.data?.let { UserMapper.map(it) })
            }

            is OperationResult.Error -> {
                OperationResult.Error(Exception())
            }
        }
    }

    suspend fun signUpUser(user: User): OperationResult<Boolean> {
        return userRepository.saveUser(user)
    }
}