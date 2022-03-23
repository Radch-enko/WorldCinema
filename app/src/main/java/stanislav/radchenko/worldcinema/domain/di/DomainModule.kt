package stanislav.radchenko.worldcinema.domain.di

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.domain.repository.AuthorizationRepository
import stanislav.radchenko.worldcinema.domain.repository.AuthorizationRepositoryImpl

val domainModule = module {
    single<AuthorizationRepository> { AuthorizationRepositoryImpl(get(), get()) }
}