package stanislav.radchenko.worldcinema.wear.screens.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.androidx.compose.getViewModel
import stanislav.radchenko.worldcinema.wear.R
import stanislav.radchenko.worldcinema.wear.activity.main.MainActivityViewModel
import stanislav.radchenko.worldcinema.wear.screens.main.MainScreen
import stanislav.radchenko.worldcinema.wear.ui.common.ButtonDefault
import stanislav.radchenko.worldcinema.wear.ui.common.TextFieldDefault

class SignInScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<SignInScreenViewModel>()
        val navigator = LocalNavigator.current
        val parentViewModel = getViewModel<MainActivityViewModel>()

        LaunchedEffect(viewModel.effect) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    SignInScreenViewModel.Effect.NavigateToRegistration -> {
                    }
                    is SignInScreenViewModel.Effect.ShowError -> {
                        parentViewModel.showDialog(effect.message)
                    }
                    SignInScreenViewModel.Effect.OpenMainScreen -> {
                        navigator?.replace(MainScreen())
                    }
                }
            }
        }

        SigninScreenInner(viewModel)
    }

    @Composable
    fun SigninScreenInner(viewModel: SignInScreenViewModel) {
        var email by remember {
            mutableStateOf("stas.radchenko.den@gmail.com")
        }
        var password by remember {
            mutableStateOf("stas2003")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    TextFieldDefault(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        placeHolderText = stringResource(id = R.string.email)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextFieldDefault(value = password, onValueChange = {
                        password = it
                    }, placeHolderText = stringResource(id = R.string.password))
                }
            }
            item {
                ButtonDefault(onClick = {
                    viewModel.sendAction(
                        SignInScreenViewModel.Action.Login(
                            email,
                            password
                        )
                    )
                }, text = stringResource(id = R.string.login))
            }
        }
    }
}