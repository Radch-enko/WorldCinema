package stanislav.radchenko.worldcinema.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.viewmodel.ext.android.getViewModel
import stanislav.radchenko.worldcinema.screens.splash.SplashStartScreen
import stanislav.radchenko.worldcinema.ui.common.ErrorDialogDefault
import stanislav.radchenko.worldcinema.ui.theme.CodGray
import stanislav.radchenko.worldcinema.ui.theme.NightRider
import stanislav.radchenko.worldcinema.ui.theme.WorldCinemaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = getViewModel<MainActivityViewModel>()
            val dialog by viewModel.dialog.collectAsState()
            val systemUiController = rememberSystemUiController()

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = NightRider,
                    darkIcons = false
                )
                systemUiController.setNavigationBarColor(
                    color = CodGray,
                    darkIcons = false
                )
            }

            WorldCinemaTheme {
                ProvideWindowInsets {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding(), color = NightRider
                    ) {
                        Navigator(SplashStartScreen())
                    }

                    // Error dialog showing
                    ErrorDialogDefault(
                        dialog,
                        onDismissRequest = { viewModel.closeDialog() },
                        onOkClick = { viewModel.closeDialog() })
                }

            }
        }
    }
}