package com.siral.food_application.Pages.SignUpScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference

open class SignUpScreenModel:ViewModel() {

    var name by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var address by mutableStateOf("")

    var isNameError by mutableStateOf(false)
    var isEmailError by mutableStateOf(false)
    var isPasswordError by mutableStateOf(false)
    var isConfirmPasswordError by mutableStateOf(false)
    var isPhoneNumberError by mutableStateOf(false)
    var isPasswordVisible by mutableStateOf(false)
    var isConfirmPasswordVisible by mutableStateOf(false)
    var isAddressError by mutableStateOf(false)
    var isAccountExists by mutableStateOf(false)

    lateinit var database: DatabaseReference
}