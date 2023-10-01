package com.rukavina.common.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    content: @Composable (SnackbarData) -> Unit
) {

    SnackbarHost(
        hostState = snackbarHostState, modifier = modifier
    ) { snackbarData ->
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Snackbar(snackbarData = snackbarData)
        }
    }
}