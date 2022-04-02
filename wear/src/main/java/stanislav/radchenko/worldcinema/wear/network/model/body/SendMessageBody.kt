package stanislav.radchenko.worldcinema.wear.network.model.body

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SendMessageBody(
    @SerialName("text")
    val text: String
)
