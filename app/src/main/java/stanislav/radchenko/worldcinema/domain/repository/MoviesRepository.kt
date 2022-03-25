package stanislav.radchenko.worldcinema.domain.repository

import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.model.response.MoviesResponse
import stanislav.radchenko.worldcinema.network.model.response.MoviesResponseItem

interface MoviesRepository {
    suspend fun getMovies(): ResultWrapper<List<MoviesResponseItem>>
}