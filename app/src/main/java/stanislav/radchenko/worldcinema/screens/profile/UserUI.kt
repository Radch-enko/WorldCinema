package stanislav.radchenko.worldcinema.screens.profile

import stanislav.radchenko.worldcinema.network.model.response.UserResponse

class UserUI(
    val userId: String,
    val name: String,
    val avatar: String,
    val email: String
)


fun UserResponse.toUI(): UserUI {
    return UserUI(
        userId = this.userId,
        name = "${this.firstName} ${this.lastName}",
        avatar = this.avatar,
        email = this.email
    )
}