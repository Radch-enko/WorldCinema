package stanislav.radchenko.worldcinema.screens.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import coil.compose.AsyncImage
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.screens.ScreenWithBottomNav
import stanislav.radchenko.worldcinema.ui.common.imagerequests.ProfileImageLoader

class ProfileScreen : Screen, ScreenWithBottomNav {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ProfileScreenViewModel>()
        val state by viewModel.state.collectAsState()

        ProfileScreenInner(state)

    }

    @Composable
    fun ProfileScreenInner(state: ProfileScreenViewModel.State) {
        Box(modifier = Modifier.padding(16.dp)) {
            when (state) {
                ProfileScreenViewModel.State.Error -> {

                }
                ProfileScreenViewModel.State.Loading -> {

                }
                is ProfileScreenViewModel.State.User -> {
                    UserProfile((state as ProfileScreenViewModel.State.User).userUI)
                }
            }
        }
    }

    @Composable
    fun UserProfile(userUI: UserUI) {
        Row {
            Column() {
                AsyncImage(
                    model = ProfileImageLoader.load(userUI.avatar, LocalContext.current),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .border(1.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(stringResource(id = R.string.change_profile_data))
                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = userUI.name, style = typography.h4)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = userUI.email)
                }
            }
        }
    }
}