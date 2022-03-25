package stanislav.radchenko.worldcinema.screens.main

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import stanislav.radchenko.worldcinema.navigation.BottomNavigation

class MainScreen : Screen {
    @Composable
    override fun Content() {
        BottomNavigation()
    }
}