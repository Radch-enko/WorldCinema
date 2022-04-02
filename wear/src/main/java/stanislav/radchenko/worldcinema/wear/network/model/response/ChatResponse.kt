package stanislav.radchenko.worldcinema.wear.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ChatResponse(
    @SerialName("chatId")
    val chatId: String,
    @SerialName("name")
    val name: String
)
