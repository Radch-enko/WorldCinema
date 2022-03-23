package stanislav.radchenko.worldcinema.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import stanislav.radchenko.worldcinema.BuildConfig
import stanislav.radchenko.worldcinema.screens.signin.AuthorizationTokenUseCase


@OptIn(ExperimentalSerializationApi::class)
class RetrofitBuilder(private val authorizationTokenUseCase: AuthorizationTokenUseCase) {

    private val contentType = "application/json".toMediaType()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${authorizationTokenUseCase.getToken()}")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(getOkHttpClient())
            .build()
    }

    val apiService: WorldCinemaService = getRetrofit().create(WorldCinemaService::class.java)
}