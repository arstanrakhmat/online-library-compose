package com.example.onlinelibrary.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    //    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { tast ->
                    if (tast.isSuccessful) {
                        Log.d("FB", "signInWithEmailAndPassword: SUCCESS ${tast.result}")
//                    TODO: take them home
                        home()
                    } else {
                        Log.d("FB", "signInWithEmailAndPassword: FAIL ${tast.result}")
                    }
                }
            } catch (ex: Exception) {
                Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
            }
        }

    fun createUserWithEmailAndPassword() {

    }


}