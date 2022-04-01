package stanislav.radchenko.worldcinema.screens.chat

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.domain.repository.ChatRepository
import stanislav.radchenko.worldcinema.domain.repository.UserRepository
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.screens.profile.UserUI
import stanislav.radchenko.worldcinema.screens.profile.toUI

class ChatScreenViewModel(
    private val id: String,
    private val repository: ChatRepository,
    private val userRepository: UserRepository,
) : ScreenModel {

    sealed class State {
        object Loading : State()
        class Error(val message: String) : State()
        class Chat(val chatUI: ChatUI, val myProfile: UserUI) : State()
    }

    sealed class Action {
        class SendMessage(val message: String) : Action()
    }

    private val mutableState = MutableStateFlow<State>(State.Loading)
    val state = mutableState.asStateFlow()

    init {
        initialLoadData()
    }

    private fun initialLoadData() = coroutineScope.launch {
        mutableState.value = State.Loading

        while (true) {
            loadData()
            delay(500)
        }
    }

    private fun loadData() = coroutineScope.launch(CoroutineExceptionHandler { _, _ ->
        setErrorState("Что-то пошло не так, попробуйте позже")
    }) {
        repository.getUserProfile().combine(repository.getChat(id)) { user, chat ->
            State.Chat(ChatUI(chat.chatId, chat.name, listOf()), user.toUI())
        }.collectLatest { chat: State.Chat ->
            loadMessages(chat)
        }
    }

    fun sendAction(
        action: Action
    ) = coroutineScope.launch {
        when (action) {
            is Action.SendMessage -> sendMessage(action.message)
        }
    }

    private fun sendMessage(message: String) = coroutineScope.launch {
        when (val response =
            repository.sendMessage((state.value as State.Chat).chatUI.chatId, message)) {
            is ResultWrapper.GenericError -> response.error?.message?.let { setErrorState(it) }
            ResultWrapper.NetworkError -> setErrorState(response.toString())
            is ResultWrapper.Success -> {
                loadData()
            }
        }
    }

    private fun loadMessages(chat: State.Chat) = coroutineScope.launch {
        val chatState = chat.chatUI

        when (val result = repository.getMessages(chatState.chatId)) {
            is ResultWrapper.GenericError -> {
                setErrorState("Произошла ошибка, попробуйте позже")
            }
            ResultWrapper.NetworkError -> result.toString()
            is ResultWrapper.Success -> {
                mutableState.value = State.Chat(
                    ChatUI(
                        chatId = chatState.chatId,
                        title = chatState.title,
                        messages = result.value.toUI()
                    ),
                    myProfile = chat.myProfile
                )
            }
        }
    }

    private fun setErrorState(message: String) {
        mutableState.value = State.Error(message)
    }
}