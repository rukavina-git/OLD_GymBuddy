package com.rukavina.auth

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    var isUsernameValid by remember { mutableStateOf(true) }
    var passwordStrengthMessage by remember { mutableStateOf("") }
    var doPasswordsMatch by remember { mutableStateOf(true) }


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

        if (!isUsernameValid) {
            Text(
                text = "Username should be at least 4 characters long and contain only lowercase letters and numbers.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        TextField(
            value = username,
            onValueChange = {
                username = it
                isUsernameValid = authViewModel.isUsernameValid(it)
            },
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
            ),
            isError = !isUsernameValid,
            visualTransformation = VisualTransformation.None,
        )


        // Password TextField with strength validation
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                // @todo Display the password strength message with color instead of just displaying error
                // This will be improved in future
                if (getPasswordStrengthMessage(password) != "Valid password") {
                    Text(
                        text = passwordStrengthMessage,
                        color = getPasswordStrengthColor(passwordStrengthMessage),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordStrengthMessage = getPasswordStrengthMessage(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(
                            width = 1.dp,
                            color = getPasswordStrengthColor(passwordStrengthMessage),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    singleLine = true,
                    placeholder = { Text(text = "Password") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { confirmPasswordFocusRequester.requestFocus() }
                    ),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val passwordToggleIcon = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(
                            onClick = { isPasswordVisible = !isPasswordVisible },
                            modifier = Modifier
                                .padding(end = 8.dp)
                        ) {
                            Icon(imageVector = passwordToggleIcon, contentDescription = "Toggle Password Visibility")
                        }
                    }
                )
            }
        }

        // Confirm Password TextField with matching logic
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                // Display an error message if passwords don't match
                if (!doPasswordsMatch) {
                    Text(
                        text = "Passwords do not match",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }

                TextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        doPasswordsMatch = it == password
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(
                            width = 1.dp,
                            color = if (doPasswordsMatch) Color.Gray else Color.Red,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    singleLine = true,
                    placeholder = { Text(text = "Confirm Password") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val passwordToggleIcon = if (isConfirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(
                            onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible },
                            modifier = Modifier
                                .padding(end = 8.dp)
                        ) {
                            Icon(imageVector = passwordToggleIcon, contentDescription = "Toggle Password Visibility")
                        }
                    }
                )
            }
        }
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

fun getPasswordStrengthMessage(password: String): String {
    // Return a message indicating the strength, e.g., "Weak," "Medium," or "Strong"

    val length = password.length
    val hasLowerCase = password.any { it.isLowerCase() }
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasDigit = password.any { it.isDigit() }

    return when {
        length < 8 || !hasLowerCase || !hasUpperCase || !hasDigit -> "Password must be a minimum of 8 characters and include at least one uppercase letter, one lowercase letter, and one digit."
        else -> "Valid password"
    }
}

fun getPasswordStrengthColor(strengthMessage: String): Color {
    // Set the color based on the strength message

    return when (strengthMessage) {
        //@todo implement color strength check later on
        "Password must be a minimum of 8 characters and include at least one uppercase letter, one lowercase letter, and one digit." -> Color.Red
        "Valid password" -> Color.Black
        else -> Color.Black
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        onSignUpClick = {},
        navController = rememberNavController()
    )
}