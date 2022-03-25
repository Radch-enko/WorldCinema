package stanislav.radchenko.worldcinema.activity.main

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import stanislav.radchenko.worldcinema.screens.signin.AuthorizationTokenUseCase
import stanislav.radchenko.worldcinema.ui.DialogState

class MainActivityViewModel(private val authorizationTokenUseCase: AuthorizationTokenUseCase) :
    ViewModel(), ScreenModel {

    private val mutableDialogState = MutableStateFlow<DialogState>(DialogState("", false))
    val dialog = mutableDialogState.asStateFlow()

    fun showDialog(message: String) {
        mutableDialogState.value = DialogState(message, true)
    }

    fun closeDialog() {
        mutableDialogState.value = DialogState(dialog.value.message, false)
    }
}