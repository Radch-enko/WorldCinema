package stanislav.radchenko.worldcinema.wear.screens.splash

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import stanislav.radchenko.worldcinema.wear.screens.main.MainScreen
import stanislav.radchenko.worldcinema.wear.screens.signin.AuthorizationTokenUseCase
import stanislav.radchenko.worldcinema.wear.screens.signin.SignInScreen

class SplashStartScreenViewModel(private val authorizationTokenUseCase: AuthorizationTokenUseCase) :
    ScreenModel {

    fun checkAuthToken(): Screen {
        return if (authorizationTokenUseCase.getToken() == 0) {
            SignInScreen()
        } else {
            MainScreen()
        }
    }
}