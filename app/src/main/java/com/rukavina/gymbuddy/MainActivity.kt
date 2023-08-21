package com.rukavina.gymbuddy

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rukavina.gymbuddy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    window.statusBarColor = getColor(R.color.primary)
                }
                R.id.navigation_progress -> {
                    window.statusBarColor = getColor(R.color.primary)
                }
                R.id.navigation_workout -> {
                    window.statusBarColor = getColor(R.color.primary)
                }
                R.id.navigation_exercise -> {
                    window.statusBarColor = getColor(R.color.primary)
                }
                else -> {
                    window.statusBarColor = getColor(R.color.primary)
                }
            }
            true
        }
    }
}
