package stanislav.radchenko.worldcinema.screens.chatlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.network.model.response.ChatResponse
import stanislav.radchenko.worldcinema.ui.common.ErrorScreenState
import stanislav.radchenko.worldcinema.ui.common.LoadingScreenState
import stanislav.radchenko.worldcinema.ui.common.TopBarWithBackButton

class ChatListScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ChatListViewModel>()
        val state by viewModel.state.collectAsState()

        when (state) {
            is ChatListViewModel.State.ChatsList -> {
                ChatList((state as ChatListViewModel.State.ChatsList).list, onChatClick = {})
            }
            is ChatListViewModel.State.Error -> ErrorScreenState(message = (state as ChatListViewModel.State.Error).message)
            ChatListViewModel.State.Loading -> LoadingScreenState()
        }
    }

    @Composable
    fun ChatList(list: List<ChatResponse>, onChatClick: (String) -> Unit) {
        Scaffold(topBar = {
            TopBarWithBackButton(title = stringResource(id = R.string.discussion))
        }) {
            LazyColumn(content = {
                items(list) { chat ->
                    ChatListItem(chat, onChatClick)
                }
            })
        }
    }

    @Composable
    fun ChatListItem(chat: ChatResponse, onChatClick: (String) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chat_image_example),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Column() {
                    Text(text = chat.name)
                    Row() {
                        Text(buildAnnotatedString {
                            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                append("Иван: ")
                            }
                            append("Смотрели уже последнюю серию? Я просто поверить не...")
                        })
                    }
                }
            }
            Divider(
                color = Color.Gray,
                thickness = 3.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
        }
    }
}