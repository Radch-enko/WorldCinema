package stanislav.radchenko.worldcinema.screens.signin

import android.content.Context

class AuthorizationTokenUseCase(private val context: Context) {

    companion object {
        const val prefs = "AUTHORIZATOIN_PREFS"
        const val token = "TOKEN"
    }

    private val sharedPreferences = context.getSharedPreferences(prefs, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun saveToken(responseToken: Int) {
        editor.putInt(token, responseToken)
        editor.commit()
    }

    fun getToken(): Int {
        return sharedPreferences.getInt(token, 0)
    }

    fun deleteToken() {
        editor.putInt(token, 0)
        editor.commit()
    }
}
