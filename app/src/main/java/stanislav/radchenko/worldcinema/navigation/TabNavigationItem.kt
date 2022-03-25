package stanislav.radchenko.worldcinema.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import stanislav.radchenko.worldcinema.ui.theme.Boulder
import stanislav.radchenko.worldcinema.ui.theme.Trinidad

@Composable
fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        selectedContentColor = Trinidad,
        unselectedContentColor = Boulder,
        label = { Text(tab.title) },
        icon = { tab.icon?.let { Icon(painter = it, contentDescription = tab.title) } }
    )
}