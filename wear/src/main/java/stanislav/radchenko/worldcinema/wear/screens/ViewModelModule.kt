package stanislav.radchenko.worldcinema.wear.screens

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.wear.activity.main.MainActivityViewModel
import stanislav.radchenko.worldcinema.wear.screens.chatlist.ChatListViewModel
import stanislav.radchenko.worldcinema.wear.screens.home.HomeScreenViewModel
import stanislav.radchenko.worldcinema.wear.screens.signin.SignInScreenViewModel
import stanislav.radchenko.worldcinema.wear.screens.splash.SplashStartScreenViewModel

val viewModelModule = module {
    factory { MainActivityViewModel(get()) }
    factory { SignInScreenViewModel(get(), get()) }
    factory { SplashStartScreenViewModel(get()) }
    factory { HomeScreenViewModel(get()) }
    factory { ChatListViewModel(get()) }
}