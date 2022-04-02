package stanislav.radchenko.worldcinema.wear.screens.main

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import stanislav.radchenko.worldcinema.wear.screens.collection.CollectionScreen

class MainScreen : Screen {
    @Composable
    override fun Content() {
        Navigator(screen = CollectionScreen())
    }
}