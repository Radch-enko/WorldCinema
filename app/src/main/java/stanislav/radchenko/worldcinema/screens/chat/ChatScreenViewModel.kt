package stanislav.radchenko.worldcinema.screens.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.domain.repository.ChatRepository
import stanislav.radchenko.worldcinema.domain.repository.UserRepository
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.screens.profile.UserUI
import stanislav.radchenko.worldcinema.screens.profile.toUI

class ChatScreenViewModel(
    private val id: String,
    private val repository: ChatRepository,
    private val userRepository: UserRepository
) :
    ViewModel(), ScreenModel {

    sealed class State {
        object Loading : State()
        class Error(val message: String) : State()
        class Chat(val chatUI: ChatUI, val myProfile: UserUI) : State()
    }

    sealed class Action {
        class SendMessage(val message: String) : Action()
    }

    sealed class Effect {
        object ScrollToLastItem : Effect()
    }

    private var chatId: String? = null
    private var title: String? = null

    private val mutableState = MutableStateFlow<State>(State.Loading)
    val state = mutableState.asStateFlow()

    private val mutableEffect = MutableSharedFlow<Effect>()
    val effect = mutableEffect.asSharedFlow()

    private var userProfile: UserUI? = null

    init {
        coroutineScope.launch {
            loadProfile()
            loadChatInfo()
            loadingMessages()
        }
    }

    private suspend fun loadingMessages() {
        mutableState.value = State.Loading

        while (true) {
            delay(4000)
            loadMessages()
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
                // TODO display your new message
                loadMessages()
            }
        }
    }

    private suspend fun loadProfile() {
        when (val userResponse = userRepository.getUser()) {
            is ResultWrapper.GenericError -> userResponse.error?.message?.let { setErrorState(it) }
            ResultWrapper.NetworkError -> setErrorState(userResponse.toString())
            is ResultWrapper.Success -> userProfile = userResponse.value.toUI()
        }
    }

    private fun loadChatInfo() = viewModelScope.launch {
        when (val response = repository.getChat(id)) {
            is ResultWrapper.GenericError -> response.error?.message?.let { setErrorState(it) }
            ResultWrapper.NetworkError -> setErrorState(response.toString())
            is ResultWrapper.Success -> {
                chatId = response.value.chatId
                title = response.value.name
            }
        }
    }

    private suspend fun loadMessages() {
        when (val messagesResponse = chatId?.let { repository.getMessages(it) }) {
            is ResultWrapper.GenericError -> messagesResponse.error?.message?.let { setErrorState(it) }
            ResultWrapper.NetworkError -> messagesResponse.toString()
            is ResultWrapper.Success -> {
                mutableState.value = State.Chat(
                    ChatUI(
                        chatId = chatId!!,
                        title = title!!,
                        messages = messagesResponse.value.toUI()
                    ),
                    myProfile = userProfile!!
                )
            }
        }
    }

    private fun setErrorState(message: String) {
        mutableState.value = State.Error(message)
    }
}