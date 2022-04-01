package stanislav.radchenko.worldcinema.domain.repository

import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.model.response.UserResponse

interface UserRepository {
    suspend fun getUser(): ResultWrapper<UserResponse>
    suspend fun loadAvatar(url: String): ResultWrapper<List<UserResponse>>
}