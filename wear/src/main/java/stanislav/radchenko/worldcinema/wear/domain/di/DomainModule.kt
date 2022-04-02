package stanislav.radchenko.worldcinema.wear.domain.di

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.wear.domain.repository.AuthorizationRepository
import stanislav.radchenko.worldcinema.wear.domain.repository.AuthorizationRepositoryImpl
import stanislav.radchenko.worldcinema.wear.domain.repository.ChatRepository
import stanislav.radchenko.worldcinema.wear.domain.repository.ChatRepositoryImpl
import stanislav.radchenko.worldcinema.wear.domain.repository.MoviesRepository
import stanislav.radchenko.worldcinema.wear.domain.repository.MoviesRepositoryImpl
import stanislav.radchenko.worldcinema.wear.domain.repository.UserRepository
import stanislav.radchenko.worldcinema.wear.domain.repository.UserRepositoryImpl

val domainModule = module {
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get(), get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ChatRepository> { ChatRepositoryImpl(get(), get()) }
}