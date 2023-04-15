package com.siral.food_application.Pages.SplashScreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.siral.food_application.R
import com.siral.food_application.NavigationHelpers.NavRoute
import com.siral.food_application.NavigationHelpers.NavigationState
import com.siral.food_application.ui.theme.Teal200
import com.siral.food_application.ui.theme.urbanBold
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController:NavHostController,splashScreenVM:SplashScreenVM = viewModel())
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var animation = animateFloatAsState(
            targetValue = if (splashScreenVM.startAnimation) 1f
            else 0f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        )
        LaunchedEffect(key1 = Unit)
        {
            splashScreenVM.startAnimation = true
            delay(3000)
            navController.navigate(NavRoute.LogIn.route)
        }
        Splash_Text(alpha = animation.value)
    }
}

@Composable
fun Splash_Text(alpha: Float) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.siral),
            modifier = Modifier.alpha(alpha),
            color = Teal200,
            style = urbanBold,
            fontSize = 52.sp,
            textAlign = TextAlign.Center,
            )

    }
}