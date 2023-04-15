package com.siral.food_application.NavigationHelpers

import androidx.compose.runtime.Composable

enum class NavRoute(val route:String) {
    Splash("splash"),
    SignUp("signup"),
    LogIn("logIn"),
    ListScreen("listScreen"),
    UserSelectScreen("userSelect"),
    ProfileScreen("profileScreen"),
    DonationDetailScreen("donationDetailScreen")
//    FoodFormScreen("foodFormScreen/{FormType}")
}

sealed class NavigationState(){
    data class Navigation(val route: NavRoute):NavigationState()
}

