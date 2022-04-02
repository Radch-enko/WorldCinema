package stanislav.radchenko.worldcinema.wear.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOf
import stanislav.radchenko.worldcinema.wear.network.ResultWrapper
import stanislav.radchenko.worldcinema.wear.network.WorldCinemaService
import stanislav.radchenko.worldcinema.wear.network.model.ChatMessagesResponse
import stanislav.radchenko.worldcinema.wear.network.model.body.SendMessageBody
import stanislav.radchenko.worldcinema.wear.network.model.response.ChatResponse
import stanislav.radchenko.worldcinema.wear.network.safeApiCall

class ChatRepositoryImpl(
    private val service: WorldCinemaService,
    private val dispatcher: CoroutineDispatcher
) : ChatRepository {

    override suspend fun getChat(movieId: String) = flowOf(service.getChat(movieId)[0])

    override suspend fun getMessages(chatId: String): ResultWrapper<List<ChatMessagesResponse>> {
        return safeApiCall(dispatcher, apiCall = {
            service.getChatMessages(chatId)
        })
    }


    override suspend fun getUserProfile() = flowOf(service.getUser()[0])

    override suspend fun sendMessage(
        chatId: String,
        text: String
    ): ResultWrapper<ChatMessagesResponse> {
        return safeApiCall(dispatcher, apiCall = {
            service.sendMessage(chatId = chatId, body = SendMessageBody(text))
        })
    }

    override suspend fun getMyChats(): ResultWrapper<List<ChatResponse>> {
        return safeApiCall(dispatcher, apiCall = {
            service.getMyChats()
        })
    }
}