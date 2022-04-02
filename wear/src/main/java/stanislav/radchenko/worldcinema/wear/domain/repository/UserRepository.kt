package stanislav.radchenko.worldcinema.wear.domain.repository

import stanislav.radchenko.worldcinema.wear.network.ResultWrapper
import stanislav.radchenko.worldcinema.wear.network.model.response.UserResponse

interface UserRepository {
    suspend fun getUser(): ResultWrapper<UserResponse>
    suspend fun loadAvatar(url: String): ResultWrapper<List<UserResponse>>
}