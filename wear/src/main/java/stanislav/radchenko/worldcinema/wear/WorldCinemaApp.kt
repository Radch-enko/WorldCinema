package stanislav.radchenko.worldcinema.wear

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level.ERROR
import stanislav.radchenko.worldcinema.wear.di.appModule
import stanislav.radchenko.worldcinema.wear.domain.di.domainModule
import stanislav.radchenko.worldcinema.wear.network.di.networkModule
import stanislav.radchenko.worldcinema.wear.screens.viewModelModule

class WorldCinemaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(ERROR)
            androidContext(this@WorldCinemaApp)
            modules(
                appModule,
                networkModule,
                domainModule,
                viewModelModule
            )
        }
    }
}