package stanislav.radchenko.worldcinema.wear.activity.main

import android.util.Log
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import stanislav.radchenko.worldcinema.wear.screens.signin.AuthorizationTokenUseCase
import stanislav.radchenko.worldcinema.wear.ui.DialogState

class MainActivityViewModel(private val authorizationTokenUseCase: AuthorizationTokenUseCase) :
    ViewModel(), ScreenModel {

    private val mutableDialogState = MutableStateFlow<DialogState>(DialogState("", false))
    val dialog = mutableDialogState.asStateFlow()

    init {
        showTokenForDebug()
    }

    private fun showTokenForDebug() {
        Log.e("AUTHORIZATION TOKEN", authorizationTokenUseCase.getToken().toString())
    }

    fun showDialog(message: String) {
        mutableDialogState.value = DialogState(message, true)
    }

    fun closeDialog() {
        mutableDialogState.value = DialogState(dialog.value.message, false)
    }
}