package stanislav.radchenko.worldcinema.wear.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import stanislav.radchenko.worldcinema.wear.ui.theme.Boulder

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TextFieldDefault(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    placeHolderText: String
) {

    Surface(
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(Boulder, fontSize = TextUnit(8f, TextUnitType.Sp)),
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
    }
}

@Preview
@Composable
fun TextFieldDefaultPreview() {
    TextFieldDefault("Test text", {}, placeHolderText = "Test Placeholder")
}