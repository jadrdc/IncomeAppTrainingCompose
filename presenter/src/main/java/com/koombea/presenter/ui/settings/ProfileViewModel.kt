package com.koombea.presenter.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.entity.Currency
import com.koombea.data.character.datasource.entity.Security
import com.koombea.domain.model.UserModel
import com.koombea.domain.usecase.GetUserCase
import com.koombea.domain.usecase.mapper.UserMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(private val getUserCase: GetUserCase) : ViewModel() {
    private val _state = MutableStateFlow(ProfileViewModelState())
    val state: StateFlow<ProfileViewModelState>
        get() = _state

    init {
        viewModelScope.launch {
            getUserCase.saveUser()
        }
    }

    fun setUser(user: UserModel) {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    user = user
                )
            }
        }
    }

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

    fun getUser() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            getUserCase.perform().let { result ->
                when (result) {
                    is OperationResult.Success -> _state.value =
                        _state.value.copy(loading = false, user = result.data)

                    is OperationResult.Error -> _state.value.copy(
                        loading = false, error = result.exception.toString()
                    )

                }
            }

        }
    }

    fun setUpdated() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    wasUpdated = false
                )
            }
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            with(state.value) {
                user?.let {
                    getUserCase.updateUser(UserMapper.map(it))
                    _state.update { currentState ->
                        currentState.copy(
                            wasUpdated = true
                        )
                    }
                }
            }
        }
    }

    fun setCurrency(currency: Currency) {
        viewModelScope.launch {
            with(state.value) {
                user?.let {
                    _state.update { currentState ->
                        currentState.copy(
                            user = currentState.user?.copy(
                                currency = currency
                            )
                        )
                    }
                }
            }
        }
    }

    fun setSecurity(security: Security) {
        viewModelScope.launch {
            with(state.value) {
                user?.let {
                    _state.update { currentState ->
                        currentState.copy(
                            user = currentState.user?.copy(
                                security = security
                            )
                        )
                    }
                }
            }
        }
    }

}

data class ProfileViewModelState(
    val wasUpdated: Boolean? = false,
    val loading: Boolean? = false, val user: UserModel? = null, val error: String? = ""
)
