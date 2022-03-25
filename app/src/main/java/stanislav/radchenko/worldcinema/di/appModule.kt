package stanislav.radchenko.worldcinema.di

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.screens.signin.AuthorizationTokenUseCase

val appModule = module {
    single { AuthorizationTokenUseCase(get()) }
}