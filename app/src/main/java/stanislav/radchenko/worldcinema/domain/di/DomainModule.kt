package stanislav.radchenko.worldcinema.domain.di

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.domain.repository.AuthorizationRepository
import stanislav.radchenko.worldcinema.domain.repository.AuthorizationRepositoryImpl
import stanislav.radchenko.worldcinema.domain.repository.MoviesRepository
import stanislav.radchenko.worldcinema.domain.repository.MoviesRepositoryImpl

val domainModule = module {
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get(), get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}