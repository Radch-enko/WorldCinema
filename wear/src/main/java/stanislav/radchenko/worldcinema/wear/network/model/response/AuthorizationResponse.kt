package stanislav.radchenko.worldcinema.wear.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthorizationResponse(
    @SerialName("token")
    val token: Int
)