package stanislav.radchenko.worldcinema.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.screens.ScreenWithBottomNav
import stanislav.radchenko.worldcinema.screens.chatlist.ChatListScreen
import stanislav.radchenko.worldcinema.screens.splash.SplashStartScreenViewModel
import stanislav.radchenko.worldcinema.ui.common.ErrorScreenState
import stanislav.radchenko.worldcinema.ui.common.LoadingScreenState
import stanislav.radchenko.worldcinema.ui.common.OutlinedButtonDefault
import stanislav.radchenko.worldcinema.ui.common.UploadAvatarSection

class ProfileScreen : Screen, ScreenWithBottomNav {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ProfileScreenViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.current?.parent?.parent
        val splashScreenViewModel = getScreenModel<SplashStartScreenViewModel>()

        when (state) {
            is ProfileScreenViewModel.State.Error -> {
                ErrorScreenState(message = (state as ProfileScreenViewModel.State.Error).message)
            }
            ProfileScreenViewModel.State.Loading -> {
                LoadingScreenState()
            }
            is ProfileScreenViewModel.State.User -> {
                ProfileScreenInner(
                    (state as ProfileScreenViewModel.State.User).userUI,
                    onLogOutClick = {
                        viewModel.sendAction(ProfileScreenViewModel.Action.LogOut)
                        navigator?.replace(splashScreenViewModel.checkAuthToken())
                    }, onLoadAvatar = { url ->
                        viewModel.sendAction(ProfileScreenViewModel.Action.LoadAvatar(url))
                    })
            }
        }
    }

    @Composable
    fun ProfileScreenInner(
        userUI: UserUI,
        onLogOutClick: () -> Unit,
        onLoadAvatar: (String) -> Unit
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            UserProfile(userUI, onLoadAvatar)

            Spacer(modifier = Modifier.height(30.dp))

            ProfileLinks(onLogOutClick)
        }
    }

    @Composable
    fun ProfileLinks(onLogOutClick: () -> Unit) {
        val navigator = LocalNavigator.current
        LazyColumn(content = {
            item {
                ProfileLinkItem(
                    Icons.Filled.Forum,
                    stringResource(id = R.string.discussion),
                    onClick = {
                        navigator?.push(ChatListScreen())
                    })
            }
            item {
                ProfileLinkItem(
                    Icons.Filled.WatchLater,
                    stringResource(id = R.string.history),
                    onClick = { })
            }
            item {
                ProfileLinkItem(
                    Icons.Filled.Settings,
                    stringResource(id = R.string.settings),
                    onClick = { })
            }
            item {
                OutlinedButtonDefault(
                    onClick = onLogOutClick,
                    text = stringResource(id = R.string.logout)
                )
            }
        }, verticalArrangement = Arrangement.spacedBy(16.dp))
    }

    @Composable
    fun ProfileLinkItem(icon: ImageVector, title: String, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, style = typography.h5, modifier = Modifier.weight(1f))

            IconButton(onClick = onClick) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
            }
        }
    }

    @Composable
    fun UserProfile(userUI: UserUI, onLoadAvatar: (String) -> Unit) {
        Row {
            UploadAvatarSection(LocalContext.current, userUI, onLoadAvatar)

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