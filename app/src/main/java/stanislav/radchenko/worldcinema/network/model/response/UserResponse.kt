package stanislav.radchenko.worldcinema.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserResponse(
    @SerialName("userId")
    val userId: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("avatar")
    val avatar: String,
    @SerialName("email")
    val email: String,
)