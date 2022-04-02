package stanislav.radchenko.worldcinema.wear.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.coroutines.delay
import stanislav.radchenko.worldcinema.wear.ui.common.Logo

class SplashStartScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<SplashStartScreenViewModel>()
        val navigator = LocalNavigator.current
        LaunchedEffect(key1 = true) {
            delay(1000)
            navigator?.replace(viewModel.checkAuthToken())
        }

        Logo(modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashStartScreen()
}