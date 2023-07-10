package com.koombea.data.character.datasource.remote

import com.koombea.data.character.datasource.entity.CharacterListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceCharacter {
    @GET("character/?page=1")
    suspend fun getCharacters(): Response<CharacterListResponse>
}