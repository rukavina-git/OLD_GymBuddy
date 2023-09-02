package com.rukavina.exercise.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rukavina.exercise.R
import com.rukavina.exercise.model.entities.Exercise
import com.rukavina.exercise.ui.theme.NavyBlue


@Composable
fun ExerciseCard(exercise: Exercise, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onItemClick)
            .background(
                color = Color.Red,
                shape = RoundedCornerShape(16.dp) // Set rounded corners for the outer Box
            )
    ) {
        Card(
            shape = RoundedCornerShape(16.dp), // Set rounded corners for the Card
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = exercise.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = exercise.group,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .background(NavyBlue)
                        .clip(CircleShape)
                        .clickable(onClick = onItemClick),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun ExerciseList(exercises: List<Exercise>) {
    Column {
        exercises.forEach { exercise ->
            ExerciseCard(exercise = exercise) {
                // Handle card item click here
            }
        }
    }
}

@Preview
@Composable
fun PreviewExerciseCard() {
    val sampleExercise = Exercise(1, "Sample Exercise", "Legs", "Exercise description")
    ExerciseCard(exercise = sampleExercise) {}
}

@Preview
@Composable
fun PreviewExerciseList() {
    val sampleExercises = listOf(
        Exercise(1, "Exercise 1", "Chest", "Chest exercise description"),
        Exercise(2, "Exercise 2", "Back", "Back exercise description"),
        Exercise(3, "Exercise 3", "Legs", "Legs exercise description")
    )
    ExerciseList(exercises = sampleExercises)
}