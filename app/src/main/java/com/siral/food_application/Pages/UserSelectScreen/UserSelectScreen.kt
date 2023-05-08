package com.siral.food_application.Pages.UserSelectScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.siral.food_application.NavigationHelpers.NavRoute
import com.siral.food_application.R
import com.siral.food_application.ui.theme.Teal200
import com.siral.food_application.ui.theme.urbanBold
import com.siral.food_application.ui.theme.urbanMedium
import com.siral.food_application.ui.theme.urbanSemiBold

@Composable
fun UserSelectScreen(navController: NavHostController)
{
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxSize()
        .padding(vertical = 16.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.siral),
                color = Teal200,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                style = urbanBold,
                modifier = Modifier.align(Alignment.Center)
            )
            Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "account",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(30.dp)
                    .clickable {
                        navController.navigate(NavRoute.ProfileScreen.route)
                    }.align(Alignment.CenterEnd))

        }
        Column(modifier = Modifier.fillMaxSize()
            .padding(vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            FoodCard(name = "Food Details", desc = "Click to see the donators and receivers") {
                navController.navigate(NavRoute.ListScreen.route)
            }
            FoodCard(name = "Donate Your Food", desc = "Click to donate for the food") {
                navController.navigate("foodFormScreen/Donate")
            }
            FoodCard(name = "Request Your Food", desc = "Click to get the food") {
                navController.navigate("foodFormScreen/Receive")
            }
        }
    }
}

@Composable
fun FoodCard(name:String,desc:String,onClick:()->Unit)
{
    Card(modifier = Modifier
        .padding(vertical = 8.dp, horizontal = 20.dp)
        .height(80.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onClick() }
                .padding(start = 10.dp, end = 0.dp)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 0.dp)) {
                Text(text = name, style = urbanSemiBold, fontSize = 18.sp)
                Text(text = desc, style = urbanMedium, fontSize = 13.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription ="right icon", modifier = Modifier
                .padding(end = 3.dp)
                .size(24.dp), tint = Color.Unspecified)
        }
    }
}