package com.siral.food_application.Pages.DonationDetailScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.siral.food_application.R
import com.siral.food_application.ui.theme.Teal200
import com.siral.food_application.ui.theme.urbanBold
import com.siral.food_application.ui.theme.urbanist

@Composable
fun DonationDetailScreen(navController: NavHostController, isReceiver:Boolean?,vm: DonationDetailVM = viewModel()) {
    val context = LocalContext.current
    Log.d("details", donationDetailBO.toString())
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.siral),
            color = Teal200,
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            style = urbanBold,
            modifier = Modifier.align(CenterHorizontally)
        )
        Card(
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 40.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
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
                        append(donationDetailBO.userName)
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
                        append(donationDetailBO.address)
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
                        append("Persons : ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = urbanist,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                    )
                    {
                        append(donationDetailBO.numberOfPersons)
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
                        append("Time : ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = urbanist,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                    )
                    {
                        append(donationDetailBO.time)
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
                        append("Phone : ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = urbanist,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                    )
                    {
                        append(donationDetailBO.phoneNumber)
                    }
                })
            }
        }
        Button(
            onClick =
            {
                vm.submitClicked(navController,context, isReceiver!!)
            },
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Teal200,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .height(40.dp)
                .width(200.dp)
                .align(CenterHorizontally)
        )
        {
            Text(text = if(isReceiver == true) "Donate" else "Request")
        }
    }
}