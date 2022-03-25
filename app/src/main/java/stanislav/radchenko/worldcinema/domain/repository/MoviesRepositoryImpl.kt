package stanislav.radchenko.worldcinema.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import stanislav.radchenko.worldcinema.network.ResultWrapper
import stanislav.radchenko.worldcinema.network.WorldCinemaService
import stanislav.radchenko.worldcinema.network.model.response.MoviesResponseItem
import stanislav.radchenko.worldcinema.network.safeApiCall

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