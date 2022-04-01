package stanislav.radchenko.worldcinema.domain.repository

import kotlinx.coroutines.flow.Flow
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.model.ChatMessagesResponse
import stanislav.radchenko.worldcinema.network.model.response.ChatResponse

interface ChatRepository {
    suspend fun getChat(chatId: String): ResultWrapper<ChatResponse>
    suspend fun getMessages(chatId: String): Flow<ResultWrapper<List<ChatMessagesResponse>>>
    suspend fun sendMessage(chatId: String, text: String): ResultWrapper<ChatMessagesResponse>
    suspend fun getMyChats(): ResultWrapper<List<ChatResponse>>
}