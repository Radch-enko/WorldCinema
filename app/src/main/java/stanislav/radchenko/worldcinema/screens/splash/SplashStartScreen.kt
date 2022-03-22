package stanislav.radchenko.worldcinema.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import stanislav.radchenko.worldcinema.screens.signin.SignInScreen
import stanislav.radchenko.worldcinema.ui.common.Logo

class SplashStartScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { SplashStartScreenViewModel() }
        val state by viewModel.state.collectAsState()

        when (state) {
            SplashStartScreenViewModel.State.Started -> {
                Logo(modifier = Modifier.fillMaxSize())
            }
            SplashStartScreenViewModel.State.Loaded -> {
                LocalNavigator.current?.push(SignInScreen())
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashStartScreen()
}