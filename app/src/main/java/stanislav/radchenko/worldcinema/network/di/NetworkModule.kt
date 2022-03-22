package stanislav.radchenko.worldcinema.network.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import stanislav.radchenko.worldcinema.network.RetrofitBuilder
import stanislav.radchenko.worldcinema.network.WorldCinemaService

val networkModule = module {
    single<WorldCinemaService> { RetrofitBuilder.apiService }
    single<CoroutineDispatcher> { Dispatchers.Default }
}