package com.rukavina.auth.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    fun registerUser(email: String, password: String, username: String, onComplete: (Boolean, String?) -> Unit) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registration successful, save additional user data here later on
                    // For now, we're not saving user data to Firestore
                    onComplete(true, null)
                } else {
                    onComplete(false, task.exception?.localizedMessage)
                }
            }
    }


    fun loginUser(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null)
                } else {
                    onComplete(false, task.exception?.localizedMessage)
                }
            }
    }


    fun logoutUser() {
        auth.signOut()
    }
}