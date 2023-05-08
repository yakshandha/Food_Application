package com.siral.food_application.Pages.ListScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.donationDetailBO
import com.siral.food_application.ui.theme.*

@Composable
fun ListScreen(navController:NavHostController,listScreenVM : ListScreenVM = viewModel())
{
    var isReceiver by  remember{ mutableStateOf(false)}
    LaunchedEffect(key1 = Unit)
    {
       listScreenVM.getAllDonations()
    }

    val donationList by listScreenVM.donationList.observeAsState(listOf())
    val receiverList by listScreenVM.receiverList.observeAsState(listOf())

    Column(modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "backArrow",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(30.dp)
                    .clickable {
                        navController.popBackStack()
                    }.align(CenterStart))
            Text(text = "Donate/Request Your Food",
                color = Teal200,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                style = urbanBold,
                modifier = Modifier.align(Center)
            )
//            Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "account",
//                modifier = Modifier
//                    .padding(end = 20.dp)
//                    .size(30.dp)
//                    .clickable {
//                        navController.navigate(NavRoute.ProfileScreen.route)
//                    })
        }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Donators",
            fontSize = 20.sp,
            style = urbanBold,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.CenterVertically)
                .clickable { isReceiver = false })
        Text(text = "Receivers",
            fontSize = 20.sp,
            style = urbanBold,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.CenterVertically)
                .clickable { isReceiver = true })

    }
    if(listScreenVM.loading) {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = Teal200,
                modifier = Modifier.padding(top = 10.dp))
        }
    }
    else {
            LazyColumn()
            {
                if (isReceiver) {
                    if(receiverList.isNotEmpty())
                        items(receiverList) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                .fillMaxWidth()
                                .height(150.dp)
                                .border(
                                    border = BorderStroke(2.dp, Color.LightGray)
                                )
                                .clickable {
                                    donationDetailBO = it
                                    Log.d("list", donationDetailBO.toString())
                                    navController.navigate("donationDetailScreen/$isReceiver")
                                }
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W600,
                                            fontSize = 16.sp
                                        )
                                    )
                                    {
                                        append("NAME : ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W400,
                                            fontSize = 14.sp
                                        )
                                    )
                                    {
                                        append(it.userName)
                                    }
                                })
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W600,
                                            fontSize = 16.sp
                                        )
                                    )
                                    {
                                        append("Address : ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W400,
                                            fontSize = 14.sp
                                        )
                                    )
                                    {
                                        append(it.address)
                                    }
                                })
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W600,
                                            fontSize = 16.sp
                                        )
                                    )
                                    {
                                        append("Food quantity based on persons : ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W400,
                                            fontSize = 14.sp
                                        )
                                    )
                                    {
                                        append(it.numberOfPersons)
                                    }
                                })
                                if (it.notes != null)
                                    Text(text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = urbanist,
                                                fontWeight = FontWeight.W600,
                                                fontSize = 16.sp
                                            )
                                        )
                                        {
                                            append("Notes : ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = urbanist,
                                                fontWeight = FontWeight.W400,
                                                fontSize = 14.sp
                                            )
                                        )
                                        {
                                            append(it.notes)
                                        }
                                    })
                            }
                        }
                    }
                    else
                        item{
                            Text("There is no Receivers found here!!",
                            modifier = Modifier
                                .fillMaxSize()
                                .align(CenterHorizontally))
                        }
                } else {
                    if(donationList.isNotEmpty())
                        items(donationList) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                .fillMaxWidth()
                                .height(150.dp)
                                .border(
                                    border = BorderStroke(2.dp, Color.LightGray)
                                )
                                .clickable {
                                    donationDetailBO = it
                                    Log.d("list", donationDetailBO.toString())
                                    navController.navigate("donationDetailScreen/${isReceiver}")
                                }
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W600,
                                            fontSize = 16.sp
                                        )
                                    )
                                    {
                                        append("NAME : ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W400,
                                            fontSize = 14.sp
                                        )
                                    )
                                    {
                                        append(it.userName)
                                    }
                                })
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W600,
                                            fontSize = 16.sp
                                        )
                                    )
                                    {
                                        append("Address : ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W400,
                                            fontSize = 14.sp
                                        )
                                    )
                                    {
                                        append(it.address)
                                    }
                                })
                                Text(text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W600,
                                            fontSize = 16.sp
                                        )
                                    )
                                    {
                                        append("Food quantity based on persons : ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontFamily = urbanist,
                                            fontWeight = FontWeight.W400,
                                            fontSize = 14.sp
                                        )
                                    )
                                    {
                                        append(it.numberOfPersons)
                                    }
                                })
                                if (it.notes != null)
                                    Text(text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = urbanist,
                                                fontWeight = FontWeight.W600,
                                                fontSize = 16.sp
                                            )
                                        )
                                        {
                                            append("Notes : ")
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontFamily = urbanist,
                                                fontWeight = FontWeight.W400,
                                                fontSize = 14.sp
                                            )
                                        )
                                        {
                                            append(it.notes)
                                        }
                                    })
                            }
                        }
                    }
                    else
                        item{
                            Text("There is no Donors found here!!",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(CenterHorizontally))
                        }
                }
            }

        }
    }

}