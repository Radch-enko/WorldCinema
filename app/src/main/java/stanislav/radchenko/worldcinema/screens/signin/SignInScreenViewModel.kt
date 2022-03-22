package stanislav.radchenko.worldcinema.screens.signin

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInScreenViewModel : ScreenModel {
    sealed class Action {
        object Register : Action()
    }

    sealed class Effect {
        object NavigateToRegistration : Effect()
    }

    private val mutableEffect = MutableSharedFlow<Effect>()
    val effect = mutableEffect.asSharedFlow()

    fun sendAction(action: Action) = coroutineScope.launch {
        when (action) {
            Action.Register -> mutableEffect.emit(Effect.NavigateToRegistration)
        }
    }
}