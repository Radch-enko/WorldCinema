package stanislav.radchenko.worldcinema.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.RequestBody
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.WorldCinemaService
import stanislav.radchenko.worldcinema.network.model.response.UserResponse
import stanislav.radchenko.worldcinema.network.safeApiCall
import java.io.File


class UserRepositoryImpl(
    private val service: WorldCinemaService,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun getUser(): ResultWrapper<UserResponse> {
        return safeApiCall(dispatcher, apiCall = {
            service.getUser()[0]
        })
    }

    override suspend fun loadAvatar(url: String): ResultWrapper<List<UserResponse>> {
        //pass it like this
        val file = File(url)
        val requestFile: RequestBody =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        // MultipartBody.Part is used to send also the actual file name
        val body: MultipartBody.Part = createFormData("image", file.name, requestFile)
        return safeApiCall(dispatcher, apiCall = {
            service.loadAvatar(body)
        })
    }
}