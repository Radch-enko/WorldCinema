package stanislav.radchenko.worldcinema.di

import org.koin.dsl.module
import org.koin.dsl.single
import stanislav.radchenko.worldcinema.screens.signin.AuthorizationTokenUseCase

val appModule = module {
    single<AuthorizationTokenUseCase>()
}