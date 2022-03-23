package stanislav.radchenko.worldcinema.activity.main

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.screens.signin.AuthorizationTokenUseCase
import stanislav.radchenko.worldcinema.ui.DialogState

class MainActivityViewModel(private val authorizationTokenUseCase: AuthorizationTokenUseCase) :
    ViewModel(), ScreenModel {

    sealed class State {
        object NotAuthorized : State()
        object Authorized : State()
    }

    private val mutableDialogState = MutableStateFlow<DialogState>(DialogState("", false))
    val dialog = mutableDialogState.asStateFlow()

    private val mutableState = MutableStateFlow<State>(State.Authorized)
    val state = mutableState.asStateFlow()

    init {
        checkAuthToken()
    }

    fun checkAuthToken() = coroutineScope.launch {
        if (authorizationTokenUseCase.getToken() == 0) {
            mutableState.value = State.NotAuthorized
        } else {
            mutableState.value = State.Authorized
        }
    }

    fun showDialog(message: String) {
        mutableDialogState.value = DialogState(message, true)
    }

    fun closeDialog() {
        mutableDialogState.value = DialogState(dialog.value.message, false)
    }
}