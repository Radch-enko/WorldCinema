package stanislav.radchenko.worldcinema.wear.di

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.wear.screens.signin.AuthorizationTokenUseCase

val appModule = module {
    single { AuthorizationTokenUseCase(get()) }
}