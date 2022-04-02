package stanislav.radchenko.worldcinema.wear.network.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import stanislav.radchenko.worldcinema.wear.network.RetrofitBuilder
import stanislav.radchenko.worldcinema.wear.network.WorldCinemaService

val networkModule = module {
    single<WorldCinemaService> { RetrofitBuilder(get()).apiService }
    single<CoroutineDispatcher> { Dispatchers.Default }
}