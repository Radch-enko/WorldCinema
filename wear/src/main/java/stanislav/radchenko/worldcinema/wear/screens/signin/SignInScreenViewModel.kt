package stanislav.radchenko.worldcinema.wear.screens.signin

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.wear.domain.repository.AuthorizationRepository
import stanislav.radchenko.worldcinema.wear.network.ResultWrapper
import stanislav.radchenko.worldcinema.wear.ui.utils.AuthorizationValidator

class SignInScreenViewModel(
    private val authenticationRepository: AuthorizationRepository,
    private val authorizationTokenUseCase: AuthorizationTokenUseCase
) :
    ScreenModel {
    sealed class Action {
        object ToRegistration : Action()
        class Login(val email: String, val password: String) : Action()
    }

    sealed class Effect {
        class ShowError(val message: String) : Effect()
        object NavigateToRegistration : Effect()
        object OpenMainScreen : Effect()
    }

    private val mutableEffect = MutableSharedFlow<Effect>()
    val effect = mutableEffect.asSharedFlow()

    fun sendAction(action: Action) = coroutineScope.launch {
        when (action) {
            Action.ToRegistration -> mutableEffect.emit(Effect.NavigateToRegistration)
            is Action.Login -> {
                doLogin(action.email, action.password)
            }
        }
    }

    private suspend fun doLogin(email: String, password: String) {
        val validateErrorString =
            AuthorizationValidator.validate(email, password)
        if (validateErrorString.isNullOrBlank()) {
            when (val response = authenticationRepository.login(email, password)) {
                is ResultWrapper.GenericError -> {
                    if (response.error != null) {
                        mutableEffect.emit(Effect.ShowError(response.error.message))
                    }
                }
                ResultWrapper.NetworkError -> {
                    mutableEffect.emit(Effect.ShowError("Network error, please try again"))
                }
                is ResultWrapper.Success -> {
                    authorizationTokenUseCase.saveToken(response.value.token)
                    mutableEffect.emit(Effect.OpenMainScreen)
                }
            }
        } else {
            mutableEffect.emit(Effect.ShowError(validateErrorString))
        }
    }
}