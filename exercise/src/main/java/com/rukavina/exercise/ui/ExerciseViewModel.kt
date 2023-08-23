package com.rukavina.exercise.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rukavina.exercise.model.entities.Exercise

class ExerciseViewModel : ViewModel() {

    private val _exerciseList = MutableLiveData<List<Exercise>>()
    val exerciseList: LiveData<List<Exercise>> = _exerciseList

    fun addExercise(exercise: Exercise) {
        val currentList = _exerciseList.value.orEmpty().toMutableList()
        currentList.add(exercise)
        _exerciseList.value = currentList
    }

    fun deleteExercise(exercise: Exercise) {
        val currentList = _exerciseList.value.orEmpty().toMutableList()
        currentList.remove(exercise)
        _exerciseList.value = currentList
    }
}