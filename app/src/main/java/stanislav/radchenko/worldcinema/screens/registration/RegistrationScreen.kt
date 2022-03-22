package stanislav.radchenko.worldcinema.screens.registration

import androidx.compose.foundation.layout.Box
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
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import stanislav.radchenko.worldcinema.R
import stanislav.radchenko.worldcinema.ui.common.ButtonDefault
import stanislav.radchenko.worldcinema.ui.common.Logo
import stanislav.radchenko.worldcinema.ui.common.OutlinedButtonDefault
import stanislav.radchenko.worldcinema.ui.common.TextFieldDefault

class RegistrationScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel = rememberScreenModel { RegistrationScreenViewModel() }
        val navigator = LocalNavigator.current
        LaunchedEffect(viewModel.effect) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    RegistrationScreenViewModel.Effect.ToLogin -> navigator?.pop()
                }
            }
        }

        RegistrationScreenInner(viewModel)
    }

    @Composable
    fun RegistrationScreenInner(viewModel: RegistrationScreenViewModel) {
        Box(modifier = Modifier.fillMaxSize()) {
            var name by remember {
                mutableStateOf("")
            }
            var surname by remember {
                mutableStateOf("")
            }
            var thirdname by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            var passwordAgain by remember {
                mutableStateOf("")
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo(modifier = Modifier.padding(vertical = 16.dp))

                TextFieldDefault(value = name, onValueChange = {
                    name = it
                }, placeHolderText = stringResource(id = R.string.name))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = surname, onValueChange = {
                    surname = it
                }, placeHolderText = stringResource(id = R.string.surname))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = thirdname, onValueChange = {
                    thirdname = it
                }, placeHolderText = stringResource(id = R.string.thirdname))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = password, onValueChange = {
                    password = it
                }, placeHolderText = stringResource(id = R.string.password))

                Spacer(modifier = Modifier.height(16.dp))

                TextFieldDefault(value = passwordAgain, onValueChange = {
                    passwordAgain = it
                }, placeHolderText = stringResource(id = R.string.password_again))
            }

            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                ButtonDefault(
                    onClick = { /*TODO*/ },
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