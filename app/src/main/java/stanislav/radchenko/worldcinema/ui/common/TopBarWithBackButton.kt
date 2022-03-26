package stanislav.radchenko.worldcinema.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.LocalNavigator


@Composable
fun TopBarWithBackButton(title: String) {
    val navigator = LocalNavigator.current
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navigator?.pop() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = {
            Text(text = title, style = typography.h5)
        },
        backgroundColor = Color.Transparent,
        contentColor = Color.White
    )
}

@Preview
@Composable
fun TopBarWithBackButtonPreview() {
    TopBarWithBackButton("Example page")
}