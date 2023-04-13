package com.siral.food_application.Pages.ListScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.siral.food_application.BOs.ClientDetails
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.donationBO
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.donationDetailBO
import com.siral.food_application.NavigationHelpers.NavRoute
import com.siral.food_application.ui.theme.urbanBold
import com.siral.food_application.ui.theme.urbanRegular
import com.siral.food_application.ui.theme.urbanSemiBold
import com.siral.food_application.ui.theme.urbanist

@Composable
fun ListScreen(navController:NavHostController,listScreenVM : ListScreenVM = viewModel())
{
    val list = listOf(ClientDetails("Aakash","3/556, Ambattur, Chennai"),ClientDetails("Thamizh","3/44, Poonamalle, Chennai"),
        ClientDetails("Vijay","27B, Perambur, Chennai"),
        ClientDetails("Santhosh","13, Demondy Colony, Chennai"))
    val receiverList = listOf(ClientDetails("Aadhi","1/299B, RoyalNagar, DPI"),ClientDetails("Yaksha","3/44, Vijaya Nagar, CMBT"),
        ClientDetails("Vijay","27B, Perambur, Chennai"),
        ClientDetails("Santhosh","13, Demondy Colony, Chennai"))
    var isReceiver by  remember{ mutableStateOf(false)}

    val donationList by listScreenVM.donationList.observeAsState(listOf())

    Column(modifier = Modifier.fillMaxSize())
    {
        Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "account",
        modifier = Modifier
            .padding(top = 20.dp, end = 20.dp, bottom = 20.dp)
            .align(Alignment.End)
            .size(30.dp)
            .clickable {
                navController.navigate(NavRoute.ProfileScreen.route)
            })
        
        Row(modifier = Modifier
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
        
        LazyColumn()
        {
            if(isReceiver)
            {
                items(receiverList) {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .fillMaxWidth()
                            .height(80.dp)
                            .border(
                                border = BorderStroke(2.dp, Color.LightGray)
                            )
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
                                    append(it.name)
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
                        }
                    }
                }
            }
            else {
                items(donationList) {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .fillMaxWidth()
                            .height(80.dp)
                            .border(
                                border = BorderStroke(2.dp, Color.LightGray)
                            )
                            .clickable {
                                donationDetailBO = it
                                Log.d("list", donationDetailBO.toString())
                                navController.navigate(NavRoute.DonationDetailScreen.route)
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
                        }
                    }
                }
            }
        }

    }

}