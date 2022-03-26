package stanislav.radchenko.worldcinema.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ChatMessagesResponse(
    @SerialName("chatId")
    val chatId: String,
    @SerialName("messageId")
    val messageId: String,
    @SerialName("creationDateTime")
    val creationDateTime: String,
    @SerialName("firstName")
    val firstName: String? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("avatar")
    val avatar: String,
    @SerialName("text")
    val text: String,
)
