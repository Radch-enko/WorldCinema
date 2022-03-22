package stanislav.radchenko.worldcinema.screens.registration

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegistrationScreenViewModel : ScreenModel {

    sealed class Action {
        object ToLogin : Action()
        object Register : Action()
    }

    sealed class Effect {
        object ToLogin : Effect()
    }

    private val mutableEffect = MutableSharedFlow<Effect>()
    val effect = mutableEffect.asSharedFlow()

    fun sendEvent(action: Action) = coroutineScope.launch {
        when (action) {
            Action.Register -> { /*TODO register*/
            }
            Action.ToLogin -> mutableEffect.emit(Effect.ToLogin)
        }
    }
}