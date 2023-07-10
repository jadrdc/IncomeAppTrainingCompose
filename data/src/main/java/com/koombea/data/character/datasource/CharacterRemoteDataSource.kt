package com.koombea.data.character.datasource

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.entity.CharacterEntity

interface CharacterRemoteDataSource {
    suspend fun getCharacters(): OperationResult<List<CharacterEntity>>
}