package stanislav.radchenko.worldcinema.wear.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseItem(
    @SerialName("age")
    val age: String,
    @SerialName("description")
    val description: String,
    @SerialName("images")
    val images: List<String>,
    @SerialName("movieId")
    val movieId: String,
    @SerialName("name")
    val name: String,
    @SerialName("poster")
    val poster: String,
    @SerialName("tags")
    val tags: List<Tag>
)