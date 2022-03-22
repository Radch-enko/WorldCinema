package stanislav.radchenko.worldcinema.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonDefault(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {
    Button(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun ButtonDefaultPreview() {
    ButtonDefault(onClick = { /*TODO*/ }, text = "Example button")
}