package stanislav.radchenko.worldcinema.screens

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.activity.main.MainActivityViewModel
import stanislav.radchenko.worldcinema.screens.chat.ChatScreenViewModel
import stanislav.radchenko.worldcinema.screens.chatlist.ChatListViewModel
import stanislav.radchenko.worldcinema.screens.home.HomeScreenViewModel
import stanislav.radchenko.worldcinema.screens.profile.ProfileScreenViewModel
import stanislav.radchenko.worldcinema.screens.registration.RegistrationScreenViewModel
import stanislav.radchenko.worldcinema.screens.signin.SignInScreenViewModel
import stanislav.radchenko.worldcinema.screens.splash.SplashStartScreenViewModel

val viewModelModule = module {
    factory { MainActivityViewModel(get()) }
    factory { RegistrationScreenViewModel(get()) }
    factory { SignInScreenViewModel(get(), get()) }
    factory { SplashStartScreenViewModel(get()) }
    factory { HomeScreenViewModel(get()) }
    factory { ProfileScreenViewModel(get(), get()) }
    factory { parameters -> ChatScreenViewModel(parameters.get(), get(), get()) }
    factory { ChatListViewModel(get()) }
}