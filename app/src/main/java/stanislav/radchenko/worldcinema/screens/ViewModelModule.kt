package stanislav.radchenko.worldcinema.screens

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.activity.main.MainActivityViewModel
import stanislav.radchenko.worldcinema.screens.main.MainScreenViewModel
import stanislav.radchenko.worldcinema.screens.registration.RegistrationScreenViewModel
import stanislav.radchenko.worldcinema.screens.signin.SignInScreenViewModel
import stanislav.radchenko.worldcinema.screens.splash.SplashStartScreenViewModel

val viewModelModule = module {
    single { MainActivityViewModel(get()) }
    single { RegistrationScreenViewModel(get()) }
    single { SignInScreenViewModel(get(), get()) }
    single { SplashStartScreenViewModel() }
    single { MainScreenViewModel(get()) }
}