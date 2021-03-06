package stanislav.radchenko.worldcinema.screens.registration

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.androidx.compose.getViewModel
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.activity.main.MainActivityViewModel
import stanislav.radchenko.worldcinema.ui.common.ButtonDefault
import stanislav.radchenko.worldcinema.ui.common.Logo
import stanislav.radchenko.worldcinema.ui.common.OutlinedButtonDefault
import stanislav.radchenko.worldcinema.ui.common.TextFieldDefault

class RegistrationScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<RegistrationScreenViewModel>()
        val navigator = LocalNavigator.current

        val context = LocalContext.current
        val parentViewModel = getViewModel<MainActivityViewModel>()

        LaunchedEffect(viewModel.effect) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    RegistrationScreenViewModel.Effect.ToLogin -> navigator?.pop()
                    is RegistrationScreenViewModel.Effect.ShowError -> {
                        parentViewModel.showDialog(effect.message)
                    }
                    RegistrationScreenViewModel.Effect.SuccessfullyRegistration -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.successfully),
                            Toast.LENGTH_SHORT
                        )
                    }
                }
            }
        }

        RegistrationScreenInner(viewModel)
    }

    @Composable
    fun RegistrationScreenInner(viewModel: RegistrationScreenViewModel) {
        var firstName by remember {
            mutableStateOf("")
        }
        var lastName by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var passwordAgain by remember {
            mutableStateOf("")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                Logo(modifier = Modifier.padding(vertical = 16.dp))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = firstName, onValueChange = {
                    firstName = it
                }, placeHolderText = stringResource(id = R.string.name))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = lastName, onValueChange = {
                    lastName = it
                }, placeHolderText = stringResource(id = R.string.surname))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = email, onValueChange = {
                    email = it
                }, placeHolderText = stringResource(id = R.string.email))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = password, onValueChange = {
                    password = it
                }, placeHolderText = stringResource(id = R.string.password))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = passwordAgain, onValueChange = {
                    passwordAgain = it
                }, placeHolderText = stringResource(id = R.string.password_again))
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                ButtonDefault(
                    onClick = {
                        viewModel.sendEvent(
                            RegistrationScreenViewModel.Action.Register(
                                email,
                                password,
                                passwordAgain,
                                firstName,
                                lastName
                            )
                        )
                    },
                    text = stringResource(id = R.string.registration)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButtonDefault(
                    onClick = { viewModel.sendEvent(RegistrationScreenViewModel.Action.ToLogin) },
                    text = stringResource(id = R.string.already_have_account)
                )
            }
        }
    }
}