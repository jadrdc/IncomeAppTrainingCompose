package com.koombea.androidtemplate.di

import com.koombea.domain.usecase.GetCharactersUseCase
import com.koombea.domain.usecase.GetUserCase
import com.koombea.domain.usecase.LoginUserCase
import org.koin.dsl.module


val useCaseModule = module {
    single { GetCharactersUseCase(charactersRepository = get()) }
    single { GetUserCase(userRepository = get()) }
    single { LoginUserCase(userRepository = get()) }
}