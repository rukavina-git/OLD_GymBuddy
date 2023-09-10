package com.rukavina.gymbuddy

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.rukavina.auth.AuthActivity
import com.rukavina.exercise.model.entities.Exercise
import com.rukavina.exercise.ui.ExerciseViewModel
import com.rukavina.gymbuddy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create an Intent to launch AuthActivity
        val intent = Intent(this, AuthActivity::class.java)
        // Start AuthActivity
        startActivity(intent)

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_progress, R.id.navigation_workout,
                R.id.navigation_exercise
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        exerciseViewModel.addExercise(Exercise(1, "squat", "legs", "none"))
        exerciseViewModel.addExercise(Exercise(2, "deadlift", "back", "good for back and legs"))
        exerciseViewModel.addExercise(Exercise(3, "bench", "legs", "none"))
        //exerciseViewModel.addExercise(Exercise(4, "running", "cardio", "none"))
        //exerciseViewModel.addExercise(Exercise(5, "leg press", "legs", "none"))

        //Log.d("TESTING_DATA", exerciseViewModel.exerciseList.value!![0].name)

    }
}
