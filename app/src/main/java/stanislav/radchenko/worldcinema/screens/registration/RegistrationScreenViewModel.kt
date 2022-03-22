package stanislav.radchenko.worldcinema.screens.registration

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.domain.repository.AuthorizationRepository
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.ui.utils.AuthorizationValidator

class RegistrationScreenViewModel(private val authorizationRepository: AuthorizationRepository) :
    ScreenModel {

    sealed class Action {
        object ToLogin : Action()
        class Register(
            val email: String,
            val password: String,
            val passwordAgain: String,
            val firstName: String,
            val lastName: String
        ) : Action()
    }

    sealed class Effect {
        object ToLogin : Effect()
        class ShowError(val message: String) : Effect()
        object SuccessfullyRegistration : Effect()
    }

    private val mutableEffect = MutableSharedFlow<Effect>()
    val effect = mutableEffect.asSharedFlow()

    fun sendEvent(action: Action) = coroutineScope.launch {
        when (action) {
            is Action.Register -> {
                doRegister(
                    action.email,
                    action.password,
                    action.passwordAgain,
                    action.firstName,
                    action.lastName
                )
            }
            Action.ToLogin -> mutableEffect.emit(Effect.ToLogin)
        }
    }

    private suspend fun doRegister(
        email: String,
        password: String,
        passwordAgain: String,
        firstName: String,
        lastName: String,
    ) {
        val validateErrorString =
            AuthorizationValidator.validate(email, password, passwordAgain, firstName, lastName)
        if (validateErrorString.isNullOrBlank()) {
            when (val response =
                authorizationRepository.register(email, password, firstName, lastName)) {
                is ResultWrapper.GenericError -> {
                    if (response.error != null) {
                        mutableEffect.emit(Effect.ShowError(response.error.message))
                    }
                }
                ResultWrapper.NetworkError -> {
                    mutableEffect.emit(Effect.ShowError("Network error, please try again"))
                }
                is ResultWrapper.Success -> {
                    mutableEffect.emit(Effect.SuccessfullyRegistration)
                    mutableEffect.emit(Effect.ToLogin)
                }
            }
        } else {
            mutableEffect.emit(Effect.ShowError(validateErrorString))
        }
    }
}