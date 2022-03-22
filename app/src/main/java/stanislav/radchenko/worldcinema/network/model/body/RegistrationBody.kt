package stanislav.radchenko.worldcinema.network.model.body

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RegistrationBody(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
)