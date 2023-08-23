package com.rukavina.exercise.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rukavina.exercise.model.entities.Exercise
import com.rukavina.exercise.ui.theme.GymBuddyTheme

class ExerciseFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        /*      exerciseViewModel.addExercise(Exercise(1, "squat", "legs", "none"))
                exerciseViewModel.addExercise(Exercise(2, "deadlift", "back", "good for back and legs"))
                exerciseViewModel.addExercise(Exercise(3, "bench", "legs", "none"))*/
        exerciseViewModel.addExercise(Exercise(4, "running", "cardio", "none"))
        exerciseViewModel.addExercise(Exercise(5, "leg press", "legs", "none"))

        return ComposeView(requireContext()).apply {
            setContent {
                GymBuddyTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ExerciseContent(exerciseViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseContent(exerciseViewModel: ExerciseViewModel) {
    val exercisesState = exerciseViewModel.exerciseListFlow.collectAsState(initial = emptyList())
    val exercises = exercisesState.value
    ExerciseListScreen(exercises)
}
