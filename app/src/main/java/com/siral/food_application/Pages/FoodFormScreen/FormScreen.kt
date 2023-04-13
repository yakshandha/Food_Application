package com.siral.food_application.Pages.FoodFormScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.siral.food_application.ui.theme.*

@Composable
fun FormScreen(navController: NavHostController, userType: String?,formScreenVM: FormScreenVM = viewModel())
{
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 20.dp, vertical = 20.dp)) {
        Text(
            text = if(userType=="Donate")"Donation Details" else "Receiver Details",
            color = Teal200,
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            style = urbanBold,
            modifier = Modifier.align(CenterHorizontally)
        )
        OutlinedTextField(
            value = formScreenVM.userName,
            onValueChange = { it ->
                formScreenVM.userName = it
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(56.dp)
                .onFocusChanged {
//                    if (!it.isFocused && formScreenVM.userName != "")
//                        formScreenVM.nameUnFocusChange()
                },
            singleLine = true,
            enabled = false,
            placeholder = {
                Text(
                    text = "UserName",
                    style = urbanMedium,
                    fontSize = 15.sp,
                    color = PlaceholderGray
                )
            },
            keyboardOptions = KeyboardOptions( imeAction = ImeAction.Done ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray, cursorColor = PlaceholderGray
            )
        )
        if(formScreenVM.isNameError)
            Text(
                text = "Enter a valid Name",
                color = Color.Red,
                fontSize = 12.sp,
                style = urbanSemiBold,
                modifier = Modifier.align(
                    Alignment.Start
                ).padding(top = 12.dp)

            )
        OutlinedTextField(
            value = formScreenVM.numberOfPersons,
            onValueChange = { it ->
                if(it.length<=4)
                    formScreenVM.numberOfPersons = it.filter { it.isDigit() }
            },
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .height(56.dp)
                .onFocusChanged {
//                    if (!it.isFocused && formScreenVM.numberOfPersons != "")
                },
            singleLine = true,
            placeholder = {
                Text(
                    text = "No of Persons",
                    style = urbanMedium,
                    fontSize = 15.sp,
                    color = PlaceholderGray
                )
            },
            keyboardOptions = KeyboardOptions( imeAction = ImeAction.Done ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray, cursorColor = PlaceholderGray
            )
        )
        if(formScreenVM.isPersonsError)
            Text(
                text = "Enter a valid Number of Persons",
                color = Color.Red,
                fontSize = 12.sp,
                style = urbanSemiBold,
                modifier = Modifier.align(
                    Alignment.Start
                ).padding(top = 12.dp)

            )
        OutlinedTextField(
            value = formScreenVM.address,
            onValueChange = { it ->
                formScreenVM.address = it
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(56.dp)
                .onFocusChanged {
//                    if (!it.isFocused && formScreenVM.numberOfPersons != "")
                },
            maxLines = 3,
            placeholder = {
                Text(
                    text = "Address",
                    style = urbanMedium,
                    fontSize = 15.sp,
                    color = PlaceholderGray
                )
            },
            keyboardOptions = KeyboardOptions( imeAction = ImeAction.Done ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray, cursorColor = PlaceholderGray
            )
        )
        if(formScreenVM.isAddressError)
            Text(
                text = "Enter a valid Address",
                color = Color.Red,
                fontSize = 12.sp,
                style = urbanSemiBold,
                modifier = Modifier.align(
                    Alignment.Start
                ).padding(top = 12.dp)

            )
        OutlinedTextField(
            value = formScreenVM.time,
            onValueChange = { it ->
                formScreenVM.time = it
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(56.dp)
                .onFocusChanged {
//                    if (!it.isFocused && formScreenVM.numberOfPersons != "")
                },
            singleLine = true,
            placeholder = {
                Text(
                    text = "Time",
                    style = urbanMedium,
                    fontSize = 15.sp,
                    color = PlaceholderGray
                )
            },
            keyboardOptions = KeyboardOptions( imeAction = ImeAction.Done ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray, cursorColor = PlaceholderGray
            )
        )
        if(formScreenVM.isTimeError)
            Text(
                text = "Enter a valid Time",
                color = Color.Red,
                fontSize = 12.sp,
                style = urbanSemiBold,
                modifier = Modifier.align(
                    Alignment.Start
                ).padding(top = 12.dp)

            )
        OutlinedTextField(
            value = formScreenVM.notes,
            onValueChange = { it ->
                formScreenVM.notes = it
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(150.dp)
                .onFocusChanged {
//                    if (!it.isFocused && formScreenVM.numberOfPersons != "")
                },
            maxLines = 5,
            placeholder = {
                Text(
                    text = "Notes",
                    style = urbanMedium,
                    fontSize = 15.sp,
                    color = PlaceholderGray
                )
            },
            keyboardOptions = KeyboardOptions( imeAction = ImeAction.Done ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Gray, cursorColor = PlaceholderGray
            )
        )

        Button(
            onClick =
            {
               formScreenVM.submitClicked(navController,userType,context)
            },
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Teal200,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .padding(top = 40.dp)
                .height(40.dp)
                .width(200.dp)
                .align(CenterHorizontally)
        )
        {
            Text(text = if(userType =="Donate")"Donate" else "Request")
        }
    }
}