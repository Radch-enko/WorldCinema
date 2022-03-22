package stanislav.radchenko.worldcinema.screens

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.screens.registration.RegistrationScreenViewModel
import stanislav.radchenko.worldcinema.screens.signin.SignInScreenViewModel
import stanislav.radchenko.worldcinema.screens.splash.SplashStartScreenViewModel

val viewModelModule = module {
    single { RegistrationScreenViewModel(get()) }
    single { SignInScreenViewModel() }
    single { SplashStartScreenViewModel() }
}