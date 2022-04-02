package stanislav.radchenko.worldcinema.wear.screens.home

import stanislav.radchenko.worldcinema.wear.network.model.response.MoviesResponseItem

class MovieUI(val id: String, val title: String, val imageUrl: String)

fun MoviesResponseItem.toUI(): MovieUI {
    return MovieUI(
        id = this.movieId, this.name, this.poster
    )
}
