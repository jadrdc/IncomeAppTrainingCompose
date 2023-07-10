package com.koombea.presenter.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.OperationResult
import com.koombea.domain.model.UserModel
import com.koombea.domain.usecase.LoginUserCase
import com.koombea.domain.usecase.mapper.UserMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(private val loginUserCase: LoginUserCase) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingViewModelState())
    val state: StateFlow<OnboardingViewModelState>
        get() = _state


    fun updateEmail(email: String) {
        viewModelScope.launch {
            _state.update { currentState -> currentState.copy(user = currentState.user?.copy(email = email)) }
        }
    }

    fun updateName(name: String) {
        viewModelScope.launch {
            _state.update { currentState -> currentState.copy(user = currentState.user?.copy(name = name)) }
        }
    }

    fun updateBirthday(birthday: String) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    user = currentState.user?.copy(
                        birthday = birthday
                    )
                )
            }
        }
    }

    fun updatePassword(password: String) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    user = currentState.user?.copy(
                        password = password
                    )
                )
            }
        }
    }

    fun hideValidationDialog() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    wasSignUp = null
                )
            }
        }
    }

    fun signUpUser() {
        viewModelScope.launch {
            _state.value.user?.let {
                if (it.name.isNotBlank() && it.email.isNotBlank() && it.password.isNotBlank() && it.birthday.isNotBlank()) {
                    loginUserCase.signUpUser(UserMapper.map(it)).let {
                        _state.update { currentState ->
                            currentState.copy(
                                wasSignUp = true
                            )
                        }
                    }
                } else {
                    _state.update { currentState ->
                        currentState.copy(
                            wasSignUp = false
                        )
                    }
                }
            }
        }
    }


    fun login(
        email: String = state.value.user?.email ?: "",
        password: String = state.value.user?.password ?: ""
    ) {
        viewModelScope.launch {
            loginUserCase.loginUser(email, password).let { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _state.update { currentState ->
                            currentState.copy(
                                user = result.data, wasSignUp = true
                            )
                        }
                    }

                    is OperationResult.Error -> {
                        _state.update { currentState ->
                            currentState.copy(
                                wasSignUp = false
                            )
                        }
                    }
                }
            }
        }
    }
}

data class OnboardingViewModelState(
    val wasSignUp: Boolean? = null,
    val user: UserModel? = UserModel(),
    val error: String? = ""
)
