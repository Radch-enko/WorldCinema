package stanislav.radchenko.worldcinema.wear.network.model

import kotlinx.serialization.Serializable

@Serializable
class LoginBody(val email: String, val password: String)
