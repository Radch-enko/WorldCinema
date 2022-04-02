package stanislav.radchenko.worldcinema.wear.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import stanislav.radchenko.worldcinema.wear.R
import stanislav.radchenko.worldcinema.wear.ui.common.ErrorScreenState
import stanislav.radchenko.worldcinema.wear.ui.common.imagerequests.DefaultImageLoader
import stanislav.radchenko.worldcinema.wear.ui.theme.BoulderTint

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<HomeScreenViewModel>()
        val state by viewModel.state.collectAsState()

        when (state) {
            is HomeScreenViewModel.State.Error -> ErrorScreenState(message = (state as HomeScreenViewModel.State.Error).message)
            HomeScreenViewModel.State.Loading -> SwipeContainer(
                movies = listOf(
                    MovieUI("", "Placeholder text", "")
                ),
                onClick = {},
                true
            )
            is HomeScreenViewModel.State.MovieCover -> SwipeContainer(
                (state as HomeScreenViewModel.State.MovieCover).movies,
                onClick = {}
            )
        }
    }

    @Composable
    fun SwipeContainer(
        movies: List<MovieUI>,
        onClick: (String) -> Unit,
        isPlaceholderVisible: Boolean = false
    ) {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val screenHeight = configuration.screenHeightDp.dp
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.discussion),
                        color = MaterialTheme.colors.primary
                    )
                }
            }
            items(movies) { movie ->
                Box(
                    modifier = Modifier
                        .height(screenHeight)
                        .width(screenWidth)
                ) {
                    MovieCover(movie, onClick = onClick, isPlaceholderVisible)
                }
            }
        }
    }

    @Composable
    fun MovieCover(
        movie: MovieUI,
        onClick: (String) -> Unit,
        isPlaceholderVisible: Boolean = false
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = DefaultImageLoader.load(movie.imageUrl, LocalContext.current),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .placeholder(
                        isPlaceholderVisible,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(BoulderTint, BlendMode.Difference)
            )

            Text(
                movie.title,
                modifier = Modifier
                    .placeholder(
                        isPlaceholderVisible,
                        highlight = PlaceholderHighlight.shimmer(),
                    )
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            )

            Icon(
                Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .size(64.dp)
                    .padding(16.dp), tint = MaterialTheme.colors.primary
            )
        }
    }
}