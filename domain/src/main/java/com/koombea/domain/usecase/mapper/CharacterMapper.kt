package com.koombea.domain.usecase.mapper

import com.koombea.data.character.datasource.entity.CharacterEntity
import com.koombea.domain.model.CharacterModel

class CharacterMapper {
    companion object {
        fun map(data: List<CharacterEntity>?): List<CharacterModel> {
            return data?.map {
                CharacterModel(
                    id = it.id,
                    name = it.name ?: "",
                    image = it.image ?: ""
                )
            }?.toList() ?: emptyList()
        }
    }
}