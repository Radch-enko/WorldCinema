package stanislav.radchenko.worldcinema.screens.chat

import stanislav.radchenko.worldcinema.network.model.ChatMessagesResponse

class ChatMessageUI(
    val chatId: String,
    val messageId: String,
    val creationDateTime: String,
    val name: String,
    val avatar: String,
    val text: String,
)

fun List<ChatMessagesResponse>.toUI(): List<ChatMessageUI> {
    return this.map { chatMessagesResponse ->
        chatMessagesResponse.toUI()
    }
}

private fun ChatMessagesResponse.toUI(): ChatMessageUI {
    return ChatMessageUI(
        chatId, messageId, creationDateTime, "$firstName $lastName", avatar, text
    )
}
