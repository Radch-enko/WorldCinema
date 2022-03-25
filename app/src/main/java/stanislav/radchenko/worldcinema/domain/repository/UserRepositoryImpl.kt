package stanislav.radchenko.worldcinema.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.WorldCinemaService
import stanislav.radchenko.worldcinema.network.model.response.UserResponse
import stanislav.radchenko.worldcinema.network.safeApiCall

class UserRepositoryImpl(
    private val service: WorldCinemaService,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun getUser(): ResultWrapper<UserResponse> {
        return safeApiCall(dispatcher, apiCall = {
            service.getUser()[0]
        })
    }
}