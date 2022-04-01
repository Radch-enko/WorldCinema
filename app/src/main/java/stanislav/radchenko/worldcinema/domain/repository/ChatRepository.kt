package stanislav.radchenko.worldcinema.domain.repository

import kotlinx.coroutines.flow.Flow
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.model.ChatMessagesResponse
import stanislav.radchenko.worldcinema.network.model.response.ChatResponse
import stanislav.radchenko.worldcinema.network.model.response.UserResponse

interface ChatRepository {
    suspend fun getChat(chatId: String): Flow<ChatResponse>
    suspend fun getMessages(chatId: String): ResultWrapper<List<ChatMessagesResponse>>
    suspend fun sendMessage(chatId: String, text: String): ResultWrapper<ChatMessagesResponse>
    suspend fun getMyChats(): ResultWrapper<List<ChatResponse>>
    suspend fun getUserProfile(): Flow<UserResponse>
}