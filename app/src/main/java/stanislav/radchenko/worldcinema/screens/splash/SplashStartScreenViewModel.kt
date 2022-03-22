package stanislav.radchenko.worldcinema.screens.splash

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashStartScreenViewModel :
    StateScreenModel<SplashStartScreenViewModel.State>(State.Started) {
    sealed class State {
        object Started : State()
        object Loaded : State()
    }

    init {
        coroutineScope.launch {
            delay(1000)
            mutableState.value = State.Loaded
        }
    }
}