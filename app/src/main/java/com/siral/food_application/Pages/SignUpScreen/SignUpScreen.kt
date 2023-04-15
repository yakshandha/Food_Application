package com.siral.food_application.Pages.SignUpScreen

import android.util.Log
import androidx.compose.foundation.*
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.siral.food_application.R
import com.siral.food_application.ui.theme.*

@Composable
fun SignUpScreen(navController:NavHostController,signUpVM:SignUpScreenVM = viewModel())
{
    val focusManager = LocalFocusManager.current
    val context  = LocalContext.current
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(vertical = 16.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = stringResource(id = R.string.siral),
            color = Teal200,
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            style = urbanBold,
            modifier = Modifier
                .align(CenterHorizontally)
        )
        Text(text = stringResource(R.string.setup_account),
            style = urbanSemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 44.dp)
        )
        Column(modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = signUpVM.name,
                onValueChange = { it ->
                    signUpVM.name = it
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .onFocusChanged {
                        if(!it.isFocused&& signUpVM.name!="")
                            signUpVM.nameFocusChange()
                    },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.name),
                        style = urbanMedium,
                        fontSize = 15.sp,
                        color = PlaceholderGray
                    )
                },
                leadingIcon =
                {
                    Icon(painter = painterResource(id = R.drawable.ic_person),
                        tint= PlaceholderGray,
                        contentDescription = "Email address",
                        modifier = Modifier.size(20.dp,20.dp)
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
            if(signUpVM.isNameError) {
                Text(
                    text = stringResource(R.string.enter_valid_name),
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start
                    ).padding(top = 12.dp)

                )
            }
            OutlinedTextField(
                value = signUpVM.phoneNumber,
                onValueChange = { it ->
                    if(it.length<=10)
                        signUpVM.phoneNumber = it.filter { it.isDigit() }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .onFocusChanged {
                        if(!it.isFocused&& signUpVM.phoneNumber!="")
                            signUpVM.phoneFocusChange()
                    },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.phone_num),
                        style = urbanMedium,
                        fontSize = 15.sp,
                        color = PlaceholderGray
                    )
                },
                leadingIcon =
                {
                    Icon(painter = painterResource(id = R.drawable.ic_phone_call),
                        tint= PlaceholderGray,
                        contentDescription = "Email address",
                        modifier = Modifier.size(16.dp,16.dp)
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
            if(signUpVM.isPhoneNumberError) {
                Text(
                    text = stringResource(R.string.enter_valid_phnnum),
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start
                    ).padding(top = 12.dp)

                )
            }
            OutlinedTextField(
                value = signUpVM.address,
                onValueChange = { it ->
                    signUpVM.address = it
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .onFocusChanged {
                        try {
                            if(!it.isFocused&& signUpVM.address!="")
                                signUpVM.addressFocusChange()

                        }
                        catch (e:Exception)
                        {
                            Log.d("LoginScreen",e.toString())
                        }
                    },
                placeholder = {
                    Text(
                        text = stringResource(R.string.address),
                        style = urbanMedium,
                        fontSize = 15.sp,
                        color = PlaceholderGray
                    )
                },
                leadingIcon =
                {
                    Icon(painter = painterResource(id = R.drawable.ic_address),
                        tint= PlaceholderGray,
                        contentDescription = "address",
                        modifier = Modifier.size(20.dp,20.dp)
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
                    , cursorColor = PlaceholderGray)
            )
            if(signUpVM.isAddressError) {
                Text(
                    text = stringResource(R.string.enter_valid_address) ,
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start
                    ).padding(top = 12.dp)

                )
            }
            OutlinedTextField(
                value = signUpVM.email,
                onValueChange = { it ->
                    signUpVM.email = it
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .onFocusChanged {
                        try {
                            if(!it.isFocused&& signUpVM.email!="")
                                signUpVM.emailFocusChange()

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
                        modifier = Modifier.size(20.dp,20.dp)
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
                    , cursorColor = PlaceholderGray)
            )
            if(signUpVM.isEmailError) {
                Text(
                    text =stringResource(R.string.enter_valid_email) ,
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start
                    ).padding(top = 12.dp)

                )
            }
            if(signUpVM.isAccountExists){
                Text(
                    text = stringResource(R.string.account_already_exists),
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start
                    ).padding(top = 12.dp)

                )
            }
            OutlinedTextField(
                value = signUpVM.password,
                onValueChange = { it ->
                    signUpVM.password = it.trim()
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .onFocusChanged {
                        if(!it.isFocused&& signUpVM.password!="")
                            signUpVM.pswdFocusChange()
                    },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.password),
                        style = urbanMedium,
                        fontSize = 15.sp,
                        color = PlaceholderGray
                    )
                },
                leadingIcon =
                {
                    Icon(painter = painterResource(id = R.drawable.ic_lock),
                        tint= PlaceholderGray,
                        contentDescription = "Email address",
                        modifier = Modifier.size(20.dp,20.dp)
                    )
                },
                trailingIcon = { Icon(painter = painterResource(id = if(signUpVM.isPasswordVisible) R.drawable.ic_pswd_view else R.drawable.ic_hide)
                    ,contentDescription = "Password"
                    ,tint = Color.Unspecified, modifier = Modifier.clickable(
                        interactionSource = remember{ MutableInteractionSource() },
                        indication = null
                    ){
                        signUpVM.isPasswordVisible = !signUpVM.isPasswordVisible
                    })
                },
                visualTransformation = if(signUpVM.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
            if(signUpVM.isPasswordError) {
                Text(
                    text = stringResource(R.string.enter_valid_password),
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start
                    ).padding(top = 12.dp)

                )
            }
            OutlinedTextField(
                value = signUpVM.confirmPassword,
                onValueChange = { it ->
                    signUpVM.confirmPassword = it.trim()
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .onFocusChanged {
                        if(!it.isFocused&&signUpVM.confirmPassword!="")
                            signUpVM.cnfrmPswdFocusChange()
                    },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.confirm_pswd),
                        style = urbanMedium,
                        fontSize = 15.sp,
                        color = PlaceholderGray
                    )
                },
                leadingIcon =
                {
                    Icon(painter = painterResource(id = R.drawable.ic_lock),
                        tint= PlaceholderGray,
                        contentDescription = "Email address",
                        modifier = Modifier.size(20.dp,20.dp)
                    )
                },
                trailingIcon = { Icon(painter = painterResource(id = if(signUpVM.isConfirmPasswordVisible) R.drawable.ic_pswd_view else R.drawable.ic_hide)
                    ,contentDescription = "Password"
                    ,tint = Color.Unspecified, modifier = Modifier.clickable(
                        interactionSource = remember{ MutableInteractionSource() },
                        indication = null
                    ){
                        signUpVM.isConfirmPasswordVisible = !signUpVM.isConfirmPasswordVisible
                    })
                },
                visualTransformation = if(signUpVM.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
            if(signUpVM.isConfirmPasswordError) {
                Text(
                    text = stringResource(R.string.cmnfrmpswd_error),
                    color = Color.Red,
                    fontSize = 12.sp,
                    style = urbanSemiBold,
                    modifier = Modifier.align(
                        Alignment.Start
                    ).padding(top = 12.dp)

                )
            }
            Button(
                onClick =
                {
                    signUpVM.signUpClicked(navController, context)
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
                Text(text = stringResource(R.string.submit))
            }

            Text(text = "Login Instead?",
            modifier = Modifier.padding(top = 20.dp)
            .align(CenterHorizontally)
                .clickable {
                    navController.popBackStack()
                },
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            style = urbanSemiBold,
            textDecoration = TextDecoration.Underline)
        }

    }
}