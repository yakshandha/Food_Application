package com.siral.food_application.Pages.LoginScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.siral.food_application.BOs.SignUpBO

open class LoginScreenModel:ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isEmailError by mutableStateOf(false)
    var isPasswordError by mutableStateOf(false)
    var isPasswordVisible by mutableStateOf(false)
    var isInvalidDetails by mutableStateOf(false)


    lateinit var database: DatabaseReference
    lateinit var filteredData : SnapshotStateList<SignUpBO>
}