package stanislav.radchenko.worldcinema.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import stanislav.radchenko.worldcinema.ui.theme.Mercury

@Composable
fun OutlinedButtonDefault(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {
    OutlinedButton(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        border = BorderStroke(1.dp, Mercury),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun OutlinedButtonDefaultPreview() {
    OutlinedButtonDefault(onClick = {}, text = "Test outlined button")
}