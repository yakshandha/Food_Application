package com.siral.food_application.Pages.LoginScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.siral.food_application.R
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.emailFocusChange
import com.siral.food_application.NavigationHelpers.NavRoute
import com.siral.food_application.ui.theme.*

@Composable
fun LoginScreen(navController:NavHostController,loginScreenVM: LoginScreenVM = viewModel())
{
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.siral),
            color = Teal200,
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            style = urbanBold,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(CenterHorizontally)
        )
            Text(
                stringResource(R.string.logIn),
                style = urbanSemiBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 30.dp, start = 20.dp)
            )
        Column(
            modifier = Modifier
                .padding(start = 30.dp, top = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            //Email Field
            OutlinedTextField(value = loginScreenVM.email,
                onValueChange = {
                loginScreenVM.email = it
            },
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .onFocusChanged {
                        try {
                            if(!it.isFocused&& loginScreenVM.email!="")
                               loginScreenVM.isEmailError =  emailFocusChange(loginScreenVM.email)
                        }
                        catch (e:Exception)
                        {
                            Log.d("LoginScreen",e.toString())
                        }
                    },
                placeholder = {
                    Text(
                        text = stringResource(R.string.emailAddress),
                        style = urbanMedium,
                        fontSize = 15.sp,
                        color = PlaceholderGray
                    )
                },
                leadingIcon =
                {
                    Icon(painter = painterResource(id = R.drawable.ic_email),
                    tint= PlaceholderGray,
                    contentDescription = "Email address",
                    modifier = Modifier.size(24.dp,24.dp)
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions( imeAction = ImeAction.Done ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Black
                , cursorColor = PlaceholderGray))

            if(loginScreenVM.isEmailError)
                Text(text = stringResource(R.string.enter_valid_email),
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                    Alignment.Start).padding(top=12.dp)

                )
            if(loginScreenVM.isInvalidDetails) {
                Text(text = stringResource(R.string.account_not_exists),
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start)
                )
            }
            OutlinedTextField(
                value = loginScreenVM.password,
                onValueChange = {
                   loginScreenVM.passwordValueChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp,bottom = 12.dp)
                    .height(56.dp),
                visualTransformation = if(loginScreenVM.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.password),
                        style = urbanMedium,
                        fontSize = 15.sp,
                        color = PlaceholderGray
                    )
                },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.ic_lock), contentDescription = "Email address", tint = Color.Unspecified) },
                trailingIcon = { Icon(painter = painterResource(id = if(loginScreenVM.isPasswordVisible) R.drawable.ic_pswd_view else R.drawable.ic_hide)
                        ,contentDescription = "Password"
                    ,tint = Color.Unspecified, modifier = Modifier.clickable(
                    interactionSource = remember{ MutableInteractionSource() },
                    indication = null
                    ){
                        loginScreenVM.isPasswordVisible = !loginScreenVM.isPasswordVisible
                    })
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color.Black
                , cursorColor = PlaceholderGray)
            )

            if(loginScreenVM.isPasswordError)
                Text(text = stringResource(R.string.enter_valid_password),
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start)
                )
            val context = LocalContext.current
            Button(
                onClick =
                {
                    loginScreenVM.loginClicked(navController,context)
                },
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Teal200,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(40.dp)
                    .width(150.dp)
                    .align(CenterHorizontally)
            )
            {
                Text(text = stringResource(id = R.string.logIn).uppercase())
            }

            Text(
                text = stringResource(R.string.forgot_pswd),
                style = urbanBold,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 30.dp)
                    .align(CenterHorizontally)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = "-------- ", color = Color.Black)
                IconButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(100))
                    .background(Teal200)) {
                    Text(text = "OR",
                        color = Color.Black,
                    )
                }
                Text(text = " -------- ", color = Color.Black)
            }
            Button(
                onClick =
                {
                    navController.navigate(NavRoute.SignUp.route)
                },
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Teal200,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .padding(top = 40.dp)
                    .height(40.dp)
                    .width(150.dp)
                    .align(CenterHorizontally)
            )
            {
                Text(text = stringResource(R.string.signUp).uppercase())
            }

        }
    }
}