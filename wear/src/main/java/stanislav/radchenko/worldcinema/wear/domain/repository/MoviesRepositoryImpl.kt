package stanislav.radchenko.worldcinema.wear.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import stanislav.radchenko.worldcinema.wear.network.ResultWrapper
import stanislav.radchenko.worldcinema.wear.network.WorldCinemaService
import stanislav.radchenko.worldcinema.wear.network.model.response.MoviesResponseItem
import stanislav.radchenko.worldcinema.wear.network.safeApiCall

class MoviesRepositoryImpl(
    private val service: WorldCinemaService,
    private val dispatcher: CoroutineDispatcher
) : MoviesRepository {
    override suspend fun getMovies(): ResultWrapper<List<MoviesResponseItem>> {
        return safeApiCall(dispatcher, apiCall = {
            service.getMovies()
        })
    }
}