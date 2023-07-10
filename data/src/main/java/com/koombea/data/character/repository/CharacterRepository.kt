package com.koombea.data.character.repository

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.entity.CharacterEntity

interface CharacterRepository {
     suspend fun getCharacters(): OperationResult<List<CharacterEntity>>
}