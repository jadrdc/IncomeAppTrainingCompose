package com.koombea.data.character.repository

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.CharacterRemoteDataSource
import com.koombea.data.character.datasource.entity.CharacterEntity

class CharacterRepositoryImp(private val remoteDataSource: CharacterRemoteDataSource):CharacterRepository {
    override suspend fun getCharacters(): OperationResult<List<CharacterEntity>> {
        return remoteDataSource.getCharacters()
    }

}