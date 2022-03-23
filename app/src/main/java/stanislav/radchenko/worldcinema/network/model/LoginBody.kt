package stanislav.radchenko.worldcinema.network.model

import kotlinx.serialization.Serializable

@Serializable
class LoginBody(val email: String, val password: String)
