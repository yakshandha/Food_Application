package com.siral.food_application.Pages.SplashScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

open class SplashScreenModel:ViewModel() {
    var startAnimation by mutableStateOf(false)
}