package stanislav.radchenko.worldcinema

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level.ERROR
import stanislav.radchenko.worldcinema.di.appModule

class WorldCinemaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(ERROR)
            androidContext(this@WorldCinemaApp)
            modules(appModule)
        }
    }
}