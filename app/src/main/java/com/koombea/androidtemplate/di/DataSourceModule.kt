package com.koombea.androidtemplate.di

import com.koombea.data.character.datasource.CharacterRemoteDataSource
import com.koombea.data.character.datasource.remote.CharacterRemoteDataSourceImpl
import org.koin.dsl.module


val dataSourceModule = module {
    single<CharacterRemoteDataSource> {
        CharacterRemoteDataSourceImpl(
            service = get()
        )
    }
}
