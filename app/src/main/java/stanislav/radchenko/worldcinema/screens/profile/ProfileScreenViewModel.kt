package stanislav.radchenko.worldcinema.screens.profile

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.domain.repository.UserRepository
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.screens.signin.AuthorizationTokenUseCase

class ProfileScreenViewModel(
    private val userRepository: UserRepository,
    private val authorizationTokenUseCase: AuthorizationTokenUseCase
) :
    StateScreenModel<ProfileScreenViewModel.State>(State.Loading) {

    sealed class State {
        object Loading : State()
        class User(val userUI: UserUI) : State()
        class Error(val message: String) : State()
    }

    sealed class Action {
        object LogOut : Action()
        class LoadAvatar(val url: String) : Action()
    }

    init {
        loadData()
    }

    fun sendAction(action: Action) = coroutineScope.launch {
        when (action) {
            Action.LogOut -> logOut()
            is Action.LoadAvatar -> loadAvatar(action.url)
        }
    }

    private suspend fun loadAvatar(url: String) {
        when (val response = userRepository.loadAvatar(url)) {
            is ResultWrapper.GenericError -> response.error?.message?.let { setError(it) }
            ResultWrapper.NetworkError -> setError(response.toString())
            is ResultWrapper.Success -> {
                loadData()
            }
        }
    }

    private fun logOut() {
        authorizationTokenUseCase.deleteToken()
    }

    private fun loadData() = coroutineScope.launch {
        mutableState.value = State.Loading
        when (val response = userRepository.getUser()) {
            is ResultWrapper.GenericError -> response.error?.message?.let { setError(it) }
            ResultWrapper.NetworkError -> setError(response.toString())
            is ResultWrapper.Success -> {
                mutableState.value = State.User(userUI = response.value.toUI())
            }
        }
    }

    private fun setError(message: String) {
        mutableState.value = State.Error(message)
    }
}
