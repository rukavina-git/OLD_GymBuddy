package com.rukavina.exercise.ui

import androidx.lifecycle.ViewModel
import com.rukavina.exercise.model.entities.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExerciseViewModel : ViewModel() {
    private val _exerciseListFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exerciseListFlow: StateFlow<List<Exercise>> = _exerciseListFlow

    // Use viewModelScope to update the state flow safely
    fun addExercise(exercise: Exercise) {
        val currentList = _exerciseListFlow.value.toMutableList()
        currentList.add(exercise)
        _exerciseListFlow.value = currentList
    }

    fun deleteExercise(exercise: Exercise) {
        val currentList = _exerciseListFlow.value.toMutableList()
        currentList.remove(exercise)
        _exerciseListFlow.value = currentList
    }
}