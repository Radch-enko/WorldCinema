package stanislav.radchenko.worldcinema.di

import org.koin.dsl.module
import stanislav.radchenko.worldcinema.network.di.networkModule

val appModule = module {
    networkModule
}