package stanislav.radchenko.worldcinema.screens.profile

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.domain.repository.UserRepository
import stanislav.radchenko.worldcinema.network.ResultWrapper

class ProfileScreenViewModel(private val userRepository: UserRepository) :
    StateScreenModel<ProfileScreenViewModel.State>(State.Loading) {

    sealed class State {
        object Loading : State()
        class User(val userUI: UserUI) : State()
        object Error : State()
    }

    init {
        loadData()
    }

    private fun loadData() = coroutineScope.launch {
        mutableState.value = State.Loading
        when (val response = userRepository.getUser()) {
            is ResultWrapper.GenericError -> TODO()
            ResultWrapper.NetworkError -> TODO()
            is ResultWrapper.Success -> {
                mutableState.value = State.User(userUI = response.value.toUI())
            }
        }
    }
}
