package stanislav.radchenko.worldcinema.screens.selecticon

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.ui.common.TopBarWithBackButton

class SelectIconScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {

        val icons = remember {
            mutableListOf(
                R.drawable.group_1,
                R.drawable.group_2,
                R.drawable.group_3,
                R.drawable.group_4,
                R.drawable.group_5,
                R.drawable.group_6,
                R.drawable.group_7,
                R.drawable.group_8,
                R.drawable.group_9,
                R.drawable.group_11,
                R.drawable.group_12,
                R.drawable.group_13,
                R.drawable.group_14,
                R.drawable.group_15,
                R.drawable.group_16,
                R.drawable.group_17,
                R.drawable.group_18,
                R.drawable.group_19,
                R.drawable.group_20,
                R.drawable.group_21,
                R.drawable.group_22,
                R.drawable.group_24,
                R.drawable.group_25,
                R.drawable.group_26,
                R.drawable.group_27,
                R.drawable.group_28,
                R.drawable.group_29,
                R.drawable.group_30,
                R.drawable.group_31,
                R.drawable.group_32,
                R.drawable.group_33,
                R.drawable.group_34,
                R.drawable.group_35,
                R.drawable.group_36
            )
        }

        Scaffold(topBar = {
            TopBarWithBackButton(title = stringResource(id = R.string.select_icon))
        }) { innerPadding ->
            LazyVerticalGrid(cells = GridCells.Fixed(4), content = {
                itemsIndexed(icons) { index, icon ->
                    Image(painter = painterResource(id = icon), contentDescription = null)
                }
            }, verticalArrangement = Arrangement.spacedBy(16.dp))
        }
    }
}
