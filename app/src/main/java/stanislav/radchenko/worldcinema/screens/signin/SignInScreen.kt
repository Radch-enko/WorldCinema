package stanislav.radchenko.worldcinema.screens.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.androidx.compose.getViewModel
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.activity.main.MainActivityViewModel
import stanislav.radchenko.worldcinema.screens.home.HomeScreen
import stanislav.radchenko.worldcinema.screens.registration.RegistrationScreen
import stanislav.radchenko.worldcinema.ui.common.ButtonDefault
import stanislav.radchenko.worldcinema.ui.common.Logo
import stanislav.radchenko.worldcinema.ui.common.OutlinedButtonDefault
import stanislav.radchenko.worldcinema.ui.common.TextFieldDefault

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
                        navigator?.push(RegistrationScreen())
                    }
                    is SignInScreenViewModel.Effect.ShowError -> {
                        parentViewModel.showDialog(effect.message)
                    }
                    SignInScreenViewModel.Effect.OpenMainScreen -> {
                        navigator?.replace(HomeScreen())
                    }
                }
            }
        }

        SigninScreenInner(viewModel)
    }

    @Composable
    fun SigninScreenInner(viewModel: SignInScreenViewModel) {
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Logo(
                Modifier
                    .padding(top = 50.dp)
            )

            TextFieldDefault(value = email, onValueChange = {
                email = it
            }, placeHolderText = stringResource(id = R.string.email))

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldDefault(value = password, onValueChange = {
                password = it
            }, placeHolderText = stringResource(id = R.string.password))

            ButtonDefault(onClick = {
                viewModel.sendAction(
                    SignInScreenViewModel.Action.Login(
                        email,
                        password
                    )
                )
            }, text = stringResource(id = R.string.login))
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButtonDefault(
                onClick = {
                    viewModel.sendAction(SignInScreenViewModel.Action.ToRegistration)
                },
                text = stringResource(id = R.string.registration)
            )
        }
    }
}