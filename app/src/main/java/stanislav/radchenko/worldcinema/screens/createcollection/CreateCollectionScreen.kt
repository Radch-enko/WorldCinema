package stanislav.radchenko.worldcinema.screens.createcollection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.screens.selecticon.SelectIconScreen
import stanislav.radchenko.worldcinema.ui.common.ButtonDefault
import stanislav.radchenko.worldcinema.ui.common.OutlinedButtonDefault
import stanislav.radchenko.worldcinema.ui.common.TextFieldDefault
import stanislav.radchenko.worldcinema.ui.common.TopBarWithBackButton

class CreateCollectionScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(topBar = {
            TopBarWithBackButton(title = stringResource(id = R.string.create_collection))
        }) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                CreateCollectionScreenInner()
            }
        }
    }

    @Composable
    fun CreateCollectionScreenInner() {
        val navigator = LocalNavigator.current
        var collectionName by remember {
            mutableStateOf("")
        }
        var collectionIcon by remember {
            mutableStateOf(Icons.Default.Favorite)
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(32.dp),
            content = {
                item {
                    TextFieldDefault(
                        value = collectionName,
                        onValueChange = { collectionName = it },
                        placeHolderText = stringResource(
                            id = R.string.collection_name
                        )
                    )
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            modifier = Modifier
                                .size(80.dp),
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        ) {
                            Icon(
                                collectionIcon,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))

                        OutlinedButtonDefault(
                            onClick = { navigator?.push(SelectIconScreen()) },
                            text = stringResource(id = R.string.select_icon)
                        )
                    }
                }

                item {
                    ButtonDefault(onClick = { /*TODO*/ }, text = stringResource(id = R.string.save))
                }
            })
    }
}
