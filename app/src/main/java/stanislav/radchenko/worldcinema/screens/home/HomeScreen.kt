package stanislav.radchenko.worldcinema.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import stanislav.radchenko.worldcinema.screens.ScreenWithBottomNav
import stanislav.radchenko.worldcinema.screens.chat.ChatScreen
import stanislav.radchenko.worldcinema.ui.common.ErrorScreenState
import stanislav.radchenko.worldcinema.ui.common.imagerequests.DefaultImageLoader

class HomeScreen : Screen, ScreenWithBottomNav {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<HomeScreenViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.current

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
                onClick = { movieId ->
                    navigator?.push(ChatScreen(movieId))
                }
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
        LazyRow(
            modifier = Modifier.fillMaxSize()
        ) {
            items(movies) { movie ->
                Box(
                    modifier = Modifier
                        .width(screenWidth)
                        .fillMaxHeight()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
                .clickable {
                    if (!isPlaceholderVisible) {
                        onClick(movie.id)
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                movie.title,
                style = typography.h4,
                textAlign = TextAlign.Center,
                modifier = Modifier.placeholder(
                    isPlaceholderVisible,
                    highlight = PlaceholderHighlight.shimmer(),
                )
            )
            AsyncImage(
                model = DefaultImageLoader.load(movie.imageUrl, LocalContext.current),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(0.9f)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .placeholder(
                        isPlaceholderVisible,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        }
    }
}