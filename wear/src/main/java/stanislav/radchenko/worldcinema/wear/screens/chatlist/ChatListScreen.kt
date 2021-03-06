package stanislav.radchenko.worldcinema.wear.screens.chatlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import stanislav.radchenko.worldcinema.wear.R
import stanislav.radchenko.worldcinema.wear.network.model.response.ChatResponse
import stanislav.radchenko.worldcinema.wear.ui.common.ErrorScreenState
import stanislav.radchenko.worldcinema.wear.ui.common.LoadingScreenState

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.discussion),
                    color = MaterialTheme.colors.primary
                )
            }
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
            Text(text = chat.name, modifier = Modifier.padding(16.dp))
            Divider(
                color = MaterialTheme.colors.primary,
                thickness = 3.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
        }
    }
}