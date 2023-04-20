package com.siral.food_application.Pages.UserProfileFlow.DonationRequests

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.siral.food_application.BOs.DonationRequestBO
import com.siral.food_application.Helpers.CommonUtils.CommonUtils
import com.siral.food_application.ui.theme.*

@Composable
fun DonationRequestListScreen(navController : NavHostController,vm : DonationRequestListVM = viewModel())
{
    LaunchedEffect(key1 = Unit)
    {
        vm.getRequestedDonations()
    }
    val donationList by vm.donationRequestList.observeAsState(listOf())
    if(vm.loading) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = Teal200,
                modifier = Modifier.padding(top = 10.dp))
        }
    }
    if(vm.noRequest)
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Requests Not Found!!", style = urbanSemiBold)
        }
    else {
        Column(modifier = Modifier.fillMaxSize())
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "backArrow",
                    modifier = Modifier
                        .padding(top = 20.dp, end = 20.dp, bottom = 20.dp)
                        .size(30.dp)
                        .clickable {
                            navController.popBackStack()
                        })
                Text(
                    text = "Donation Request Details", fontSize = 20.sp, style = urbanSemiBold,
                    modifier = Modifier
                        .padding(top = 20.dp, end = 20.dp, bottom = 20.dp)
                )

            }
            LazyColumn()
            {
                items(donationList) {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .border(
                                border = BorderStroke(2.dp, Color.LightGray)
                            ),
                        elevation = 2.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Donation Details",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = urbanist,
                                        fontWeight = FontWeight.W600,
                                        fontSize = 16.sp
                                    )
                                )
                                {
                                    append("No of Persons : ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = urbanist,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp
                                    )
                                )
                                {
                                    append(it.donationBO.numberOfPersons)
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
                                    append(it.donationBO.notes)
                                }
                            })
                            Divider(
                                color = BoxGrey,
                                modifier = Modifier
                                    .padding(
                                        start = 24.dp,
                                        end = 24.dp,
                                        top = 12.dp,
                                        bottom = 16.5.dp
                                    )
                                    .alpha(0.75f)
                            )
                            Text(
                                text = "Requester Details",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = urbanist,
                                        fontWeight = FontWeight.W600,
                                        fontSize = 16.sp
                                    )
                                )
                                {
                                    append("Requester Name : ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = urbanist,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp
                                    )
                                )
                                {
                                    append(it.requesterDetail.userName)
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
                                    append(it.requesterDetail.address)
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
                                    append("Phone Number : ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = urbanist,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp
                                    )
                                )
                                {
                                    append(it.requesterDetail.phoneNumber)
                                }
                            })
                            Button(
                                onClick = { vm.changeRequestStatus(it, navController) },
                                shape = RoundedCornerShape(100),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Teal200,
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .height(40.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            {
                                Text(text = "Accept Request")
                            }
                        }
                    }
                }
            }

        }
    }

}

