package com.siral.food_application.Pages.ProfileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.siral.food_application.NavigationHelpers.NavRoute
import com.siral.food_application.Pages.UserProfileFlow.UserSelectScreen.FoodCard
import com.siral.food_application.ui.theme.Teal200
import com.siral.food_application.ui.theme.urbanBold

@Composable
fun ProfileScreen(navController: NavHostController)
{
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Text(
            text = "Profile details",
            color = Teal200,
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            style = urbanBold,
            modifier = Modifier.align(CenterHorizontally)
        )

        FoodCard(name = "Donation Details", desc = "Click to see your donation status") {
            navController.navigate(NavRoute.DonationRequestScreen.route)
        }
        FoodCard(name = "Request Details", desc = "Click to see your request status") {
//            navController.navigate(NavRoute.ListScreen.route)
        }
    }
}