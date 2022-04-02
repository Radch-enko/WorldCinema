package stanislav.radchenko.worldcinema.wear.screens.collection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import stanislav.radchenko.worldcinema.wear.R
import stanislav.radchenko.worldcinema.wear.screens.chatlist.ChatListScreen
import stanislav.radchenko.worldcinema.wear.screens.home.HomeScreen

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

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun CollectionScreenInner() {
        val navigator = LocalNavigator.current?.parent
        LazyVerticalGrid(cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                item {
                    CollectionListItem(
                        Icons.Filled.Forum,
                        stringResource(id = R.string.discussion),
                        onClick = {
                            navigator?.push(ChatListScreen())
                        })
                }
                item {
                    CollectionListItem(
                        Icons.Default.Schedule,
                        stringResource(id = R.string.movies),
                        onClick = {
                            navigator?.push(HomeScreen())
                        })
                }
                item {
                    CollectionListItem(
                        Icons.Default.Flight,
                        stringResource(id = R.string.to_road),
                        onClick = {})
                }
            })
    }

    @Composable
    fun CollectionListItem(icon: ImageVector, title: String, onClick: () -> Unit) {
        Column(
            modifier = Modifier
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally
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
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = title, style = typography.overline)
        }
    }
}
