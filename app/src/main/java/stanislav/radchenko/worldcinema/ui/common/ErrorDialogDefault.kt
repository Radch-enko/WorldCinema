package stanislav.radchenko.worldcinema.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.ui.theme.Mercury

@Composable
fun ErrorDialogDefault(
    message: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onOkClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest, buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = onOkClick) {
                    Text(stringResource(id = R.string.ok))
                }
            }
        },
        title = {
            Text(
                text = message,
                color = Mercury,
                style = typography.h6
            )
        }, modifier = modifier.padding(16.dp)
    )
}