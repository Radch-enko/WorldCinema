package stanislav.radchenko.worldcinema.screens.registration

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.domain.repository.AuthorizationRepository
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.ui.DialogState
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
        object ShowError : Effect()
        object SuccessfullyRegistration : Effect()
    }

    private val mutableDialogState = MutableStateFlow<DialogState>(DialogState(""))
    val dialog = mutableDialogState.asStateFlow()

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
                    mutableDialogState.value = DialogState("Something went wrong")
                    mutableEffect.emit(Effect.ShowError)
                }
                ResultWrapper.NetworkError -> {
                    mutableDialogState.value = DialogState("Network error, please try again")
                    mutableEffect.emit(Effect.ShowError)
                }
                is ResultWrapper.Success -> {
                    mutableEffect.emit(Effect.SuccessfullyRegistration)
                    mutableEffect.emit(Effect.ToLogin)
                }
            }
        } else {
            mutableDialogState.value = DialogState(validateErrorString)
            mutableEffect.emit(Effect.ShowError)
        }
    }
}