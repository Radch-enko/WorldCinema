package stanislav.radchenko.worldcinema.wear.domain.repository

import stanislav.radchenko.worldcinema.wear.network.ResultWrapper
import stanislav.radchenko.worldcinema.wear.network.model.response.MoviesResponseItem

interface MoviesRepository {
    suspend fun getMovies(): ResultWrapper<List<MoviesResponseItem>>
}