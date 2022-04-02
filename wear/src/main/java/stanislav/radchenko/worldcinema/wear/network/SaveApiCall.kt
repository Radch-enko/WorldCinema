package stanislav.radchenko.worldcinema.wear.network

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
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
                is IOException -> {
                    throwable.printStackTrace()
                    ResultWrapper.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBodyFromString(throwable)
                    ResultWrapper.GenericError(code, ErrorResponse(errorResponse.orEmpty()))
                }
                else -> {
                    Log.e("SafeApiCall", throwable.printStackTrace().toString())
                    ResultWrapper.GenericError(error = ErrorResponse("Нет данных, попробуйте позже"))
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

private fun convertErrorBodyFromString(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val inputStream = BufferedInputStream(it.inputStream())
            val contents = ByteArray(1024)

            var bytesRead = 0
            var strFileContents: String? = null
            while (inputStream.read(contents).also { bytesRead = it } != -1) {
                strFileContents += String(contents, 0, bytesRead)
            }
            return strFileContents?.substring(4)
        }
    } catch (exception: Exception) {
        null
    }
}

fun getRealPathFromURI_API19(context: Context, uri: Uri?): String {
    var filePath = ""
    val wholeID = DocumentsContract.getDocumentId(uri)

    // Split at colon, use second item in the array
    val id = wholeID.split(":").toTypedArray()[1]
    val column = arrayOf(MediaStore.Images.Media.DATA)

    // where id is equal to
    val sel = MediaStore.Images.Media._ID + "=?"
    val cursor: Cursor? = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        column, sel, arrayOf(id), null
    )
    if (cursor != null) {
        val columnIndex: Int = cursor.getColumnIndex(column[0])
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex)
        }
    }
    cursor?.close()
    return filePath
}