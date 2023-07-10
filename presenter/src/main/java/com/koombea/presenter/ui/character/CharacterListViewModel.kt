package com.koombea.presenter.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.OperationResult
import com.koombea.domain.model.CharacterModel
import com.koombea.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityState())
    val state: StateFlow<MainActivityState>
        get() = _state

    fun getCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(loading = true)
            getCharactersUseCase.perform().let { result ->
                when (result) {
                    is OperationResult.Success -> showCharacterList(result.data)
                    is OperationResult.Error -> onGetCharacterListError(result)
                }
            }
        }
    }

    private fun showCharacterList(list: List<CharacterModel>?) {
        if (list?.isNotEmpty() == true) {
            _state.value = _state.value.copy(loading = false, list = list)
        } else {
            _state.value = _state.value.copy(loading = false, list = emptyList())
        }
    }

    private fun onGetCharacterListError(result: OperationResult.Error) {
        _state.value = _state.value.copy(loading = false, list = emptyList(),
        error = result.exception?.message )
    }
}

data class MainActivityState(
    val loading: Boolean? = false,
    val list: List<CharacterModel>? = listOf(),
    val error: String? = ""
)

