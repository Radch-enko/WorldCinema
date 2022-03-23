package stanislav.radchenko.worldcinema.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import stanislav.radchenko.worldcinema.ui.theme.CodGray

@Composable
fun BottomNavigation() {
    TabNavigator(HomeTab) {
        Scaffold(
            content = {
                CurrentTab()
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