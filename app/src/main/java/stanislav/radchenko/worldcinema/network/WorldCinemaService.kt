package stanislav.radchenko.worldcinema.network

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import stanislav.radchenko.worldcinema.network.model.LoginBody
import stanislav.radchenko.worldcinema.network.model.body.RegistrationBody
import stanislav.radchenko.worldcinema.network.model.response.AuthorizationResponse
import stanislav.radchenko.worldcinema.network.model.response.MoviesResponseItem
import stanislav.radchenko.worldcinema.network.model.response.UserResponse

interface WorldCinemaService {

    @POST("/auth/register")
    suspend fun register(@Body registrationBody: RegistrationBody): ResponseBody

    @POST("/auth/login")
    suspend fun login(@Body loginBody: LoginBody): AuthorizationResponse

    @GET("/movies?filter=new")
    suspend fun getMovies(): List<MoviesResponseItem>

    @GET("/user")
    suspend fun getUser(): List<UserResponse>
}