package com.siral.food_application.Pages.SignUpScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.text.font.FontLoadingStrategy.Companion.Async
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.siral.food_application.BOs.SignUpBO
import com.siral.food_application.Helpers.CommonUtils.CommonUtils
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.userDetail
import com.siral.food_application.NavigationHelpers.NavRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SignUpScreenVM: SignUpScreenModel() {

    init {
        database = Firebase.database.getReference("users")
    }
    fun nameFocusChange() {
        try {
            isNameError = name==""
        }
        catch (e:Exception)
        {
            Log.d("SignUpScreen",e.toString())
        }
    }

    fun phoneFocusChange() {
        try {
            isPhoneNumberError = (phoneNumber==""||phoneNumber.length<10)
        }
        catch (e:Exception)
        {
            Log.d("SignUpScreen",e.toString())
        }
    }

    fun pswdFocusChange() {
        try {
            isPasswordError = (password==""||password.length<8)
        }
        catch (e:Exception)
        {
            Log.d("SignUpScreen",e.toString())
        }
    }

    fun signUpClicked(navController: NavHostController, context: Context) {
            try {
//                viewModelScope.launch(Dispatchers.IO) {
                    runBlocking {
                        nameFocusChange()
                        phoneFocusChange()
                        pswdFocusChange()
                        addressFocusChange()
                        emailFocusChange()
                        cnfrmPswdFocusChange()
                    }
                    if (!isEmailError && !isNameError && !isPhoneNumberError && !isPasswordError && !isConfirmPasswordError && !isAddressError && !isAccountExists) {
                        val uId: String? = database.push().key
                        if (uId != null) {
                            val signup = SignUpBO(uId,email, password, phoneNumber, name, address)
                            database.child(uId).setValue(signup)
                                .addOnSuccessListener {
                                    navController.navigate(NavRoute.UserSelectScreen.route)
                                    {
                                        popUpTo(NavRoute.Splash.route) {
                                            inclusive = true
                                        }
                                    }
                                    userDetail = signup
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

                        }
                    }
//                }

            } catch (e: Exception) {
                Log.d("SignUpScreen", e.toString())
            }
    }

    fun cnfrmPswdFocusChange() {
        isConfirmPasswordError = password!=confirmPassword || confirmPassword==""

    }

    fun emailFocusChange(){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                isEmailError = CommonUtils.emailFocusChange(email.trim())
                if (!isEmailError)
                    database.orderByChild("emailId").equalTo(email).addValueEventListener(object :
                        ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            Log.d("dataa", "entered")

                            if (snapshot.exists()) {
                                Log.d("dataa", "has snapshot")

                                for (dataValues in snapshot.children) {
                                    val data = dataValues.getValue<SignUpBO>()
                                    Log.d("dataa", data.toString())
                                    if (data!!.emailId == email) {
                                        isEmailError = false
                                        isAccountExists = true
                                        return
                                    }
                                }
                                isAccountExists = false
                            }
                            isAccountExists = false
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                else
                    isAccountExists = false
            }
        }
        catch (e:Exception)
        {
            Log.d("SignUpScreen",e.toString())
        }
    }

    fun addressFocusChange() {
        try {
            isAddressError = (address==""||address.length>200)
        }
        catch (e:Exception)
        {
            Log.d("SignUpScreen",e.toString())
        }
    }


}