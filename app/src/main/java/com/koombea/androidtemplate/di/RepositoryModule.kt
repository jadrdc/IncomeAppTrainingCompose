package com.koombea.androidtemplate.di

import com.koombea.data.character.repository.CharacterRepository
import com.koombea.data.character.repository.CharacterRepositoryImp
import com.koombea.data.character.repository.UserRepository
import com.koombea.data.character.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CharacterRepository> {
        CharacterRepositoryImp(
            remoteDataSource = get()
        )
    }
    single<UserRepository> {
        UserRepositoryImpl(
            couchbaseDatabase = get()
        )
    }
}