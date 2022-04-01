package stanislav.radchenko.worldcinema.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import stanislav.radchenko.worldcinema.ui.theme.CodGray

@Composable
fun BottomNavigation() {
    TabNavigator(HomeTab) { tabNavigator ->
        Scaffold(
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    CurrentTab()
                }
            },
            bottomBar = {
                androidx.compose.material.BottomNavigation(
                    backgroundColor = CodGray
                ) {
                    TabNavigationItem(HomeTab)
                    TabNavigationItem(SelectionTab)
                    TabNavigationItem(CollectionTab)
                    TabNavigationItem(ProfileTab)
                }
            }
        )
    }
}