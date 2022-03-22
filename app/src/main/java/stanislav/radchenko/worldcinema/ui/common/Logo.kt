package stanislav.radchenko.worldcinema.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.ui.theme.Trinidad

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.app_icon), contentDescription = null)
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(top = 16.dp),
            color = Trinidad
        )
    }
}