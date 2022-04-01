package stanislav.radchenko.worldcinema.screens.collection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.screens.createcollection.CreateCollectionScreen

class CollectionScreen : Screen {
    @Composable
    override fun Content() {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CollectionScreenInner()
        }
    }

    @Composable
    fun CollectionScreenInner() {
        val navigator = LocalNavigator.current
        Scaffold(topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.collection_tab), style = typography.h4)
                IconButton(onClick = { navigator?.push(CreateCollectionScreen()) }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        }) { innerPadding ->
            LazyColumn(modifier = Modifier
                .padding(innerPadding)
                .padding(top = 50.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    item {
                        CollectionListItem(
                            Icons.Default.Favorite,
                            stringResource(id = R.string.favourite),
                            onClick = {})
                    }
                    item {
                        CollectionListItem(
                            Icons.Default.Schedule,
                            stringResource(id = R.string.sometime),
                            onClick = {})
                    }
                    item {
                        CollectionListItem(
                            Icons.Default.Flight,
                            stringResource(id = R.string.to_road),
                            onClick = {})
                    }
                })
        }
    }

    @Composable
    fun CollectionListItem(icon: ImageVector, title: String, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .size(40.dp),
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = title, style = typography.h5)
            }

            IconButton(onClick = onClick, enabled = false) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
            }
        }
    }
}
