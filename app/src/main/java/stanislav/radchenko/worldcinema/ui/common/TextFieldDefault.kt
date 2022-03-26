package stanislav.radchenko.worldcinema.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import stanislav.radchenko.worldcinema.ui.theme.Mercury

@Composable
fun TextFieldDefault(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    placeHolderText: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            unfocusedBorderColor = Mercury,
            textColor = Mercury
        ),
        placeholder = {
            Text(text = placeHolderText, color = Mercury)
        }
    )
}

@Preview
@Composable
fun TextFieldDefaultPreview() {
    TextFieldDefault("Test text", {}, placeHolderText = "Test Placeholder")
}