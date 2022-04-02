package stanislav.radchenko.worldcinema.wear.network

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import stanislav.radchenko.worldcinema.wear.network.model.ChatMessagesResponse
import stanislav.radchenko.worldcinema.wear.network.model.LoginBody
import stanislav.radchenko.worldcinema.wear.network.model.body.RegistrationBody
import stanislav.radchenko.worldcinema.wear.network.model.body.SendMessageBody
import stanislav.radchenko.worldcinema.wear.network.model.response.AuthorizationResponse
import stanislav.radchenko.worldcinema.wear.network.model.response.ChatResponse
import stanislav.radchenko.worldcinema.wear.network.model.response.MoviesResponseItem
import stanislav.radchenko.worldcinema.wear.network.model.response.UserResponse

interface WorldCinemaService {

    @POST("/auth/register")
    suspend fun register(@Body registrationBody: RegistrationBody): ResponseBody

    @POST("/auth/login")
    suspend fun login(@Body loginBody: LoginBody): AuthorizationResponse

    @GET("/movies?filter=new")
    suspend fun getMovies(): List<MoviesResponseItem>

    @GET("/user")
    suspend fun getUser(): List<UserResponse>

    @GET("/chats/{movieId}")
    suspend fun getChat(
        @Path("movieId") chatId: String
    ): List<ChatResponse>

    @GET("/chats/{chatId}/messages")
    suspend fun getChatMessages(
        @Path("chatId") chatId: String
    ): List<ChatMessagesResponse>

    @POST("/chats/{chatId}/messages")
    suspend fun sendMessage(
        @Path("chatId") chatId: String,
        @Body body: SendMessageBody
    ): ChatMessagesResponse

    @Multipart
    @POST("/user/avatar")
    suspend fun loadAvatar(
        @Part image: MultipartBody.Part
    ): List<UserResponse>

    @GET("/user/chats")
    suspend fun getMyChats(): List<ChatResponse>
}