package stanislav.radchenko.worldcinema.screens.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.launch
import stanislav.radchenko.worldcinema.domain.repository.MoviesRepository
import stanislav.radchenko.worldcinema.network.ResultWrapper

class HomeScreenViewModel(private val moviesRepository: MoviesRepository) :
    StateScreenModel<HomeScreenViewModel.State>(State.Loading) {

    sealed class State {
        object Loading : State()
        class Error(val message: String) : State()
        class MovieCover(val movies: List<MovieUI>) : State()
    }

    init {
        loadData()
    }

    private fun loadData() = coroutineScope.launch {
        mutableState.value = State.Loading

        when (val response = moviesRepository.getMovies()) {
            is ResultWrapper.GenericError -> {
                if (response.error != null) {
                    mutableState.value = State.Error(response.error.message)
                }
            }
            ResultWrapper.NetworkError -> {
                mutableState.value = State.Error("Network error, please try again")
            }
            is ResultWrapper.Success -> {
                mutableState.value = State.MovieCover(movies = response.value.map { movie ->
                    movie.toUI()
                })
            }
        }
    }
}
