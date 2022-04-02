package stanislav.radchenko.worldcinema.wear.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import stanislav.radchenko.worldcinema.wear.screens.splash.SplashStartScreen
import stanislav.radchenko.worldcinema.wear.ui.theme.NightRider
import stanislav.radchenko.worldcinema.wear.ui.theme.WorldCinemaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WorldCinemaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(), color = NightRider
                ) {
                    Navigator(screen = SplashStartScreen())
                }
            }
        }
    }
}