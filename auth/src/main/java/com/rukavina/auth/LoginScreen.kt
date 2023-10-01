package com.rukavina.auth

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rukavina.auth.viewmodels.AuthViewModel
import com.rukavina.common.ui.AppSnackbar
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: () -> Unit, navController: NavController
) {
    val tag = "LoginScreen"
    val authViewModel: AuthViewModel = viewModel()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    var snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val focusManager = LocalFocusManager.current
        Text(
            text = stringResource(R.string.welcome_auth),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(emailFocusRequester),
            singleLine = true,
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(passwordFocusRequester),
            singleLine = true,
            placeholder = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onDone = {
                passwordFocusRequester.requestFocus()
                keyboardController?.hide()
                onLoginClick()
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    //@todo extract this logic
                    authViewModel.loginUser(email, password) { isSuccess, error ->
                        if (isSuccess) {
                            // Login successful
                            Log.d(tag, "Login successful")
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Login successful.")
                            }
                        } else {
                            // Account not found
                            if (error != null && error.contains("no user record")) {
                                Log.d(tag, "Login error. Please check your credentials.")
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Login failed. Please check your credentials.")
                                }
                            } else {
                                // Other login errors
                                Log.d(
                                    tag, "Login error: Login failed. Please check your credentials."
                                )
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Login failed. Please check your credentials.")
                                }
                            }
                        }
                    }
                } else {
                    // Empty input
                    Log.d(tag, "Login error: Email and password cannot be empty.")
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Email and password cannot be empty.")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Log In")
        }


        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { navController.navigate("register") },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Don't have an account? Sign up")
        }
    }

    AppSnackbar(
        snackbarHostState = snackbarHostState,
        modifier = Modifier.padding(16.dp),
    ) { snackbarData ->
        Snackbar(snackbarData = snackbarData)
    }


}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginClick = {}, navController = rememberNavController()
    )
}