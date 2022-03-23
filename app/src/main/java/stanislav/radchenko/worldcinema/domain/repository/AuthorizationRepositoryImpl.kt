package stanislav.radchenko.worldcinema.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.ResponseBody
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.WorldCinemaService
import stanislav.radchenko.worldcinema.network.model.LoginBody
import stanislav.radchenko.worldcinema.network.model.body.RegistrationBody
import stanislav.radchenko.worldcinema.network.model.response.AuthorizationResponse
import stanislav.radchenko.worldcinema.network.safeApiCall

class AuthorizationRepositoryImpl(
    private val service: WorldCinemaService,
    private val dispatcher: CoroutineDispatcher
) :
    AuthorizationRepository {

    override suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): ResultWrapper<ResponseBody> {
        return safeApiCall(dispatcher, apiCall = {
            service.register(RegistrationBody(email, password, firstName, lastName))
        })
    }

    override suspend fun login(
        email: String,
        password: String
    ): ResultWrapper<AuthorizationResponse> {
        return safeApiCall(dispatcher, apiCall = {
            service.login(LoginBody(email, password))
        })
    }
}