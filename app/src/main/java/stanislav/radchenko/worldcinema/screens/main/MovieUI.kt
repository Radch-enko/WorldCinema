package stanislav.radchenko.worldcinema.screens.main

import stanislav.radchenko.worldcinema.BuildConfig
import stanislav.radchenko.worldcinema.network.model.response.MoviesResponseItem

class MovieUI(val title: String, val imageUrl: String)

fun MoviesResponseItem.toUI(): MovieUI {
    return MovieUI(
        this.name, "${BuildConfig.BASE_URL}/up/images/${this.poster}"
    )
}
