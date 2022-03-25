package stanislav.radchenko.worldcinema.screens

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.activity.main.MainActivityViewModel
import stanislav.radchenko.worldcinema.screens.home.HomeScreenViewModel
import stanislav.radchenko.worldcinema.screens.profile.ProfileScreenViewModel
import stanislav.radchenko.worldcinema.screens.registration.RegistrationScreenViewModel
import stanislav.radchenko.worldcinema.screens.signin.SignInScreenViewModel
import stanislav.radchenko.worldcinema.screens.splash.SplashStartScreenViewModel

val viewModelModule = module {
    single { MainActivityViewModel(get()) }
    single { RegistrationScreenViewModel(get()) }
    single { SignInScreenViewModel(get(), get()) }
    single { SplashStartScreenViewModel(get()) }
    single { HomeScreenViewModel(get()) }
    single { ProfileScreenViewModel(get()) }
}