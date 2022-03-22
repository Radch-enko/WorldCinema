package stanislav.radchenko.worldcinema.network

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST
import stanislav.radchenko.worldcinema.network.model.body.RegistrationBody

interface WorldCinemaService {

    @POST("/auth/register")
    suspend fun register(@Body registrationBody: RegistrationBody): ResponseBody
}