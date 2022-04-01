package stanislav.radchenko.worldcinema.screens.chatlist

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.domain.repository.ChatRepository
import stanislav.radchenko.worldcinema.network.model.response.ChatResponse

class ChatListViewModel(private val chatRepository: ChatRepository) :
    StateScreenModel<ChatListViewModel.State>(State.Loading) {
    sealed class State {
        object Loading : State()
        class Error(val message: String) : State()
        class ChatsList(val list: List<ChatResponse>) : State()
    }

    init {
        loadData()
    }

    private fun loadData() = coroutineScope.launch {

        mutableState.value = State.Loading
        delay(500)
//        when (val response = chatRepository.getMyChats()) {
//            is ResultWrapper.GenericError -> {
//                response.error?.message?.let { setErrorState(it) }
//            }
//            ResultWrapper.NetworkError -> {
//                setErrorState(response.toString())
//            }
//            is ResultWrapper.Success -> {
//                mutableState.value = State.ChatsList(list = response.value)
//            }
//        }

        mutableState.value = State.ChatsList(
            listOf(
                ChatResponse("1", "Игра престолов"),
                ChatResponse("2", "Игра престолов"),
                ChatResponse("3", "Игра престолов"),
                ChatResponse("4", "Игра престолов"),
            )
        )
    }

    private fun setErrorState(message: String) {
        mutableState.value = State.Error(message)
    }
}