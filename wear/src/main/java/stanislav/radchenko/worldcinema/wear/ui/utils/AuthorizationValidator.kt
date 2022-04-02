package stanislav.radchenko.worldcinema.wear.ui.utils

import android.util.Patterns

object AuthorizationValidator {

    fun validate(
        email: String,
        password: String,
        passwordAgain: String,
        firstName: String,
        lastName: String
    ): String? {
        return when {
            firstName.isBlank() -> "First name is empty!"
            lastName.isBlank() -> "Last name is empty!"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Incorrect email!"
            password.isBlank() -> "Password is empty!"
            password != passwordAgain -> "The passwords don't match"
            else -> null
        }
    }

    fun validate(
        email: String,
        password: String
    ): String? {
        return when {
            email.isBlank() -> "Incorrect email!"
            password.isBlank() -> "Password is empty!"
            else -> null
        }
    }
}