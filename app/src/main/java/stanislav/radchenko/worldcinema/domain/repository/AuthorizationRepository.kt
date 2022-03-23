package stanislav.radchenko.worldcinema.domain.repository

import okhttp3.ResponseBody
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.model.response.AuthorizationResponse

interface AuthorizationRepository {

    suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): ResultWrapper<ResponseBody>

    suspend fun login(
        email: String,
        password: String
    ): ResultWrapper<AuthorizationResponse>
}