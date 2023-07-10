package com.koombea.data.character.datasource.remote

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.CharacterRemoteDataSource
import com.koombea.data.character.datasource.entity.CharacterEntity
import java.lang.Exception

class CharacterRemoteDataSourceImpl(private val service: ServiceCharacter):
    CharacterRemoteDataSource {
    override suspend fun getCharacters(): OperationResult<List<CharacterEntity>> {
        val result = service.getCharacters()
        result.let {
            return if(it.isSuccessful && it.body() != null){
                OperationResult.Success(it.body()!!.results)
            }else{
                OperationResult.Error(Exception("Error"))
            }
        }
    }
}