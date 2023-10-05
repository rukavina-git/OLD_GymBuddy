package com.rukavina.gymbuddy

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.rukavina.auth.AuthActivity
import com.rukavina.exercise.model.entities.Exercise
import com.rukavina.exercise.ui.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start AuthActivity
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        exerciseViewModel.addExercise(Exercise(1, "squat", "legs", "none"))
        exerciseViewModel.addExercise(Exercise(2, "deadlift", "back", "good for back and legs"))
        exerciseViewModel.addExercise(Exercise(3, "bench", "legs", "none"))
        //exerciseViewModel.addExercise(Exercise(4, "running", "cardio", "none"))
        //exerciseViewModel.addExercise(Exercise(5, "leg press", "legs", "none"))

        //Log.d("TESTING_DATA", exerciseViewModel.exerciseList.value!![0].name)

    }
}
