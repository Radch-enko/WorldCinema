package stanislav.radchenko.worldcinema.activity.authorization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import org.koin.androidx.viewmodel.ext.android.getViewModel
import stanislav.radchenko.worldcinema.screens.splash.SplashStartScreen
import stanislav.radchenko.worldcinema.ui.common.ErrorDialogDefault
import stanislav.radchenko.worldcinema.ui.theme.WorldCinemaTheme

class AuthorizationActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = getViewModel<AuthorizationActivityViewModel>()
            val dialog by viewModel.dialog.collectAsState()
            WorldCinemaTheme {
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
