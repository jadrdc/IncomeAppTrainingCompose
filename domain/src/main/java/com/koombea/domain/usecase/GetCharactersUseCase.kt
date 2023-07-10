package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.repository.CharacterRepository
import com.koombea.domain.model.CharacterModel
import com.koombea.domain.usecase.mapper.CharacterMapper

class GetCharactersUseCase(private val charactersRepository: CharacterRepository) {
    suspend fun perform(): OperationResult<List<CharacterModel>> {
      return  when(val result =  charactersRepository.getCharacters()){
              is OperationResult.Success -> {
                  OperationResult.Success(CharacterMapper.map(result.data))
              }
              is OperationResult.Error -> {
                  OperationResult.Error(Exception())
              }
        }
    }
}