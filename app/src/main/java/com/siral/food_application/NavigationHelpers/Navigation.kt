package com.siral.food_application.NavigationHelpers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.siral.food_application.Pages.DonationDetailScreen.DonationDetailScreen
import com.siral.food_application.Pages.FoodFormScreen.FormScreen
import com.siral.food_application.Pages.ListScreen.ListScreen
import com.siral.food_application.Pages.LoginScreen.LoginScreen
import com.siral.food_application.Pages.ProfileScreen.ProfileScreen
import com.siral.food_application.Pages.SignUpScreen.SignUpScreen
import com.siral.food_application.Pages.SplashScreen.SplashScreen
import com.siral.food_application.Pages.UserSelectScreen.UserSelectScreen

@Composable
fun Navigation(navController: NavHostController)
{
    NavHost(navController = navController, startDestination = NavRoute.Splash.route)
    {
        composable(NavRoute.Splash.route){
            SplashScreen(navController)
        }

        composable(NavRoute.SignUp.route)
        {
            SignUpScreen(navController)
        }

        composable(NavRoute.LogIn.route)
        {
            LoginScreen(navController)
        }
        composable(NavRoute.ListScreen.route)
        {
            ListScreen(navController)
        }
        composable(NavRoute.UserSelectScreen.route)
        {
            UserSelectScreen(navController)
        }
        composable("foodFormScreen/{FormType}", arguments = listOf(navArgument("FormType"){type = NavType.StringType}))
        {
            FormScreen(navController,it.arguments?.getString("FormType"))
        }
        composable(NavRoute.ProfileScreen.route)
        {
            ProfileScreen(navController)
        }
        composable(NavRoute.DonationDetailScreen.route)
        {
            DonationDetailScreen(navController)
        }

    }
}