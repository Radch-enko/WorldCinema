package stanislav.radchenko.worldcinema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import cafe.adriel.voyager.navigator.Navigator
import stanislav.radchenko.worldcinema.screens.splash.SplashStartScreen
import stanislav.radchenko.worldcinema.ui.theme.WorldCinemaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldCinemaTheme {
                Surface() {
                    Navigator(screen = SplashStartScreen())
                }
            }
        }
    }
}
