package stanislav.radchenko.worldcinema.network

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.BufferedInputStream
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    Log.e("SafeApiCall", throwable.printStackTrace().toString())
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val inputStream = BufferedInputStream(it.inputStream())
            val contents = ByteArray(1024)

            var bytesRead = 0
            var strFileContents: String? = null
            while (inputStream.read(contents).also { bytesRead = it } != -1) {
                strFileContents += String(contents, 0, bytesRead)
            }
            return Json.decodeFromString<ErrorResponse>(strFileContents.orEmpty())
        }
    } catch (exception: Exception) {
        null
    }
}