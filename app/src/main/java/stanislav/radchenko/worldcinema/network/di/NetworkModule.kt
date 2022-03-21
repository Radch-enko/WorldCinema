package stanislav.radchenko.worldcinema.network.di

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.network.RetrofitBuilder
import stanislav.radchenko.worldcinema.network.WorldCinemaService

val networkModule = module {
    single<WorldCinemaService> { RetrofitBuilder.apiService }
}