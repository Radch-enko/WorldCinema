package stanislav.radchenko.worldcinema.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.viewmodel.ext.android.getViewModel
import stanislav.radchenko.worldcinema.navigation.BottomNavigation
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
            val state by viewModel.state.collectAsState()
            val systemUiController = rememberSystemUiController()

            LaunchedEffect(key1 = null, block = {
                viewModel.checkAuthToken()
            })

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
                        // your content
                        when (state) {
                            MainActivityViewModel.State.NotAuthorized -> {
                                NotAuthorized()
                            }
                            MainActivityViewModel.State.Authorized -> {
                                BottomNavigation()
                            }
                        }
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

    @Composable
    fun NotAuthorized() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Navigator(screen = SplashStartScreen())
            }
        }
    }
}