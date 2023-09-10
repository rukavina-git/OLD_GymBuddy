package com.rukavina.auth

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rukavina.auth.viewmodels.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    onSignUpClick: () -> Unit,
    navController: NavController

) {
    val TAG = "RegistrationScreen"
    val authViewModel: AuthViewModel = viewModel()

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(true) }

    val emailFocusRequester = remember { FocusRequester() }
    val usernameFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.welcome_auth),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                if (!isEmailValid) {
                    Text(
                        text = "Invalid email format",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }

                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                        isEmailValid = isEmailValid(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(
                            width = 1.dp,
                            color = if (isEmailValid) Color.Gray else Color.Red,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    singleLine = true,
                    placeholder = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { usernameFocusRequester.requestFocus() }
                    )
                )
            }
        }

        TextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(usernameFocusRequester),
            singleLine = true,
            placeholder = { Text(text = "Username") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            )
        )

        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(passwordFocusRequester),
            placeholder = { Text(text = "Password") },
            onPasswordToggleClick = { isPasswordVisible = !isPasswordVisible },
            isPasswordVisible = isPasswordVisible
        )

        PasswordTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(confirmPasswordFocusRequester),
            placeholder = { Text(text = "Confirm Password") },
            onPasswordToggleClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible },
            isPasswordVisible = isConfirmPasswordVisible
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (authViewModel.isPasswordStrong(password)) {
                    // Proceed with Firebase sign-up
                    authViewModel.registerUser(email, password, username) { isSuccess, _ ->
                        if (isSuccess) {
                            // Registration successful
                            Log.d(TAG, "Registration successful")
                            // Navigate to home screen
                        } else {
                            // Registration failed
                            Log.d(TAG, "Registration failed")
                            // Handle the failure, show an error message
                        }
                    }
                } else {
                    // Display an error message to the user
                    Log.d(TAG, "Password doesn't meet strength requirements")
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Already have an account? Log in")
        }
    }
}

// Function to check email validity
// @todo extract this
fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        onSignUpClick = {},
        navController = rememberNavController()
    )
}