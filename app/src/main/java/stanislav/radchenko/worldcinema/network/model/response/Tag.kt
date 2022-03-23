package stanislav.radchenko.worldcinema.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    @SerialName("idTags")
    val idTags: String,
    @SerialName("tagName")
    val tagName: String
)