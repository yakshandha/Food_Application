package com.siral.food_application.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.siral.food_application.R

val urbanist = FontFamily(
    Font(R.font.urbanistbold,FontWeight.W700),
    Font(R.font.urbanistsemibold,FontWeight.W600),
    Font(R.font.urbanistmedium,FontWeight.W500),
    Font(R.font.urbanistregular,FontWeight.W400)
)

val urbanBold = TextStyle(fontFamily = urbanist, fontWeight = FontWeight.W700, color = Color.Black, fontSize = 18.sp)
val urbanSemiBold = TextStyle(fontFamily = urbanist, fontWeight = FontWeight.W600, color = Color.Black, fontSize = 16.sp)
val urbanMedium = TextStyle(fontFamily = urbanist, fontWeight = FontWeight.W500, color = Color.Black, fontSize = 14.sp)
val urbanRegular = TextStyle(fontFamily = urbanist, fontWeight = FontWeight.W400, color = Color.White)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)