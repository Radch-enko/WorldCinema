package stanislav.radchenko.worldcinema.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.WorldCinemaService
import stanislav.radchenko.worldcinema.network.model.ChatMessagesResponse
import stanislav.radchenko.worldcinema.network.model.body.SendMessageBody
import stanislav.radchenko.worldcinema.network.model.response.ChatResponse
import stanislav.radchenko.worldcinema.network.safeApiCall

class ChatRepositoryImpl(
    private val service: WorldCinemaService,
    private val dispatcher: CoroutineDispatcher
) : ChatRepository {

    override suspend fun getChat(movieId: String): ResultWrapper<ChatResponse> {
        return safeApiCall(dispatcher, apiCall = {
            service.getChat(movieId)[0]
        })
    }

    override suspend fun getMessages(chatId: String) = flow {
        while (true) {
            emit(safeApiCall(dispatcher, apiCall = {
                service.getChatMessages(chatId)
            }))
            kotlinx.coroutines.delay(500)
        }
    }


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