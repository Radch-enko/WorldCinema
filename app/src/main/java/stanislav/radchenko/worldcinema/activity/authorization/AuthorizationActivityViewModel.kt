package stanislav.radchenko.worldcinema.activity.authorization

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import stanislav.radchenko.worldcinema.ui.DialogState

class AuthorizationActivityViewModel : ViewModel(), ScreenModel {

    private val mutableDialogState = MutableStateFlow<DialogState>(DialogState("", false))
    val dialog = mutableDialogState.asStateFlow()

    fun showDialog(message: String) {
        mutableDialogState.value = DialogState(message, true)
    }

    fun closeDialog() {
        mutableDialogState.value = DialogState(dialog.value.message, false)
    }
}