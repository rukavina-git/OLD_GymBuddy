package com.rukavina.exercise.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rukavina.exercise.model.entities.Exercise

@Composable
fun ExerciseListItem(exercise: Exercise) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = exercise.name)
        Text(text = exercise.group)
        Text(text = exercise.description)
    }
}