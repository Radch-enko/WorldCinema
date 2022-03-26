package stanislav.radchenko.worldcinema.domain.repository

import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.model.ChatMessagesResponse
import stanislav.radchenko.worldcinema.network.model.response.ChatResponse

interface ChatRepository {
    suspend fun getChat(chatId: String): ResultWrapper<ChatResponse>
    suspend fun getMessages(chatId: String): ResultWrapper<List<ChatMessagesResponse>>
    suspend fun sendMessage(chatId: String, text: String): ResultWrapper<ChatMessagesResponse>
}