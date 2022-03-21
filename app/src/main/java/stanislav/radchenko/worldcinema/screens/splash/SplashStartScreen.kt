package stanislav.radchenko.worldcinema.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.ui.theme.Trinidad

class SplashStartScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.app_icon), contentDescription = null)
            Text(
                text = stringResource(id = R.string.app_name),
                style = typography.h3,
                modifier = Modifier.padding(top = 16.dp),
                color = Trinidad
            )
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashStartScreen()
}