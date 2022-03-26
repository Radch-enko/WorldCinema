package stanislav.radchenko.worldcinema.screens.chat

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.ui.common.ErrorScreenState
import stanislav.radchenko.worldcinema.ui.common.LoadingScreenState
import stanislav.radchenko.worldcinema.ui.common.MediumText
import stanislav.radchenko.worldcinema.ui.common.TextFieldDefault
import stanislav.radchenko.worldcinema.ui.common.TopBarWithBackButton
import stanislav.radchenko.worldcinema.ui.common.imagerequests.ProfileImageLoader
import stanislav.radchenko.worldcinema.ui.theme.CodGray
import stanislav.radchenko.worldcinema.ui.theme.Trinidad

class ChatScreen(val id: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ChatScreenViewModel by viewModel { parametersOf(id) }

        val state by viewModel.state.collectAsState()
        ChatScreenInner(
            state,
            onSendClick = { message ->
                viewModel.sendAction(
                    ChatScreenViewModel.Action.SendMessage(
                        message
                    )
                )
            })
    }

    @Composable
    fun ChatScreenInner(state: ChatScreenViewModel.State, onSendClick: (String) -> Unit) {
        when (state) {
            ChatScreenViewModel.State.Loading -> {
                LoadingScreenState()
            }
            is ChatScreenViewModel.State.Chat -> {
                Scaffold(topBar = {
                    TopBarWithBackButton(state.chatUI.title)
                }, bottomBar = {
                    SendMessageBottomBar(onSendClick = onSendClick)
                }) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MessagesList(state)
                    }
                }
            }
            is ChatScreenViewModel.State.Error -> {
                ErrorScreenState(message = state.message)
            }
        }
    }

    @Composable
    fun MessagesList(state: ChatScreenViewModel.State.Chat) {
        val scrollState = rememberLazyListState()
        val messages = state.chatUI.messages

        LaunchedEffect(scrollState) {
            scrollState.animateScrollToItem(messages.size - 1)
        }

        LazyColumn(
            content = {
                itemsIndexed(messages) { index, message ->
                    val isAvatarVisible = if (index != messages.size - 1) {
                        messages[index].name != messages[index + 1].name
                    } else {
                        true
                    }
                    if (state.myProfile.name == message.name) {
                        OwnMessageItem(message, isAvatarVisible)
                    } else {
                        MessageItem(message, isAvatarVisible)
                    }
                }
            },
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = scrollState
        )
    }

    @Composable
    fun MessageItem(message: ChatMessageUI, isAvatarVisible: Boolean = true) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            if (isAvatarVisible) {
                AsyncImage(
                    model = ProfileImageLoader.load(message.avatar, LocalContext.current),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .border(1.dp, Color.Gray, CircleShape)
                        .align(Alignment.Bottom)
                )
            } else {
                Spacer(modifier = Modifier.size(30.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Surface(
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 0.dp
                ),
                color = CodGray,
                contentColor = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = message.text)
                    MediumText(text = "${message.name} · ${message.creationDateTime}")
                }
            }
        }
    }

    @Composable
    fun OwnMessageItem(message: ChatMessageUI, isAvatarVisible: Boolean = true) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Surface(
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 10.dp
                ),
                color = Trinidad,
                contentColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = message.text)
                    MediumText(text = "${message.name} · ${message.creationDateTime}")
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            if (isAvatarVisible) {
                AsyncImage(
                    model = ProfileImageLoader.load(message.avatar, LocalContext.current),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .border(1.dp, Color.Gray, CircleShape)
                        .align(Alignment.Bottom)
                )
            } else {
                Spacer(modifier = Modifier.size(30.dp))
            }
        }
    }

    @Composable
    fun SendMessageBottomBar(onSendClick: (String) -> Unit) {
        var messageText by remember {
            mutableStateOf("")
        }

        BottomAppBar(backgroundColor = Color.Transparent, contentPadding = PaddingValues(16.dp)) {
            TextFieldDefault(
                messageText, onValueChange = {
                    messageText = it
                }, placeHolderText = stringResource(id = R.string.write_message),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = {
                onSendClick(messageText)
            }) {
                Surface(color = Trinidad, shape = CircleShape) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = null)
                }
            }
        }

    }
}