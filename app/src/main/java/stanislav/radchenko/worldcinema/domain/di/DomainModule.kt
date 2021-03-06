package stanislav.radchenko.worldcinema.domain.di

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.domain.repository.AuthorizationRepository
import stanislav.radchenko.worldcinema.domain.repository.AuthorizationRepositoryImpl
import stanislav.radchenko.worldcinema.domain.repository.ChatRepository
import stanislav.radchenko.worldcinema.domain.repository.ChatRepositoryImpl
import stanislav.radchenko.worldcinema.domain.repository.MoviesRepository
import stanislav.radchenko.worldcinema.domain.repository.MoviesRepositoryImpl
import stanislav.radchenko.worldcinema.domain.repository.UserRepository
import stanislav.radchenko.worldcinema.domain.repository.UserRepositoryImpl

val domainModule = module {
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get(), get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<ChatRepository> { ChatRepositoryImpl(get(), get()) }
}