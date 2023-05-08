package com.siral.food_application.Pages.LoginScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.invalidateGroupsWithKey
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.siral.food_application.BOs.SignUpBO
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.emailFocusChange
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.userDetail
import com.siral.food_application.NavigationHelpers.NavRoute


class LoginScreenVM:LoginScreenModel() {

init {
     database = FirebaseDatabase.getInstance().getReference("users")
    filteredData = mutableStateListOf()
}
    fun passwordValueChange(password: String) {
        try {
            this.password = password
            isPasswordError = password == ""
        }
        catch(e:Exception)
        {
            Log.d("LoginScreen",e.toString())
        }

    }

    fun loginClicked(navController: NavHostController, context: Context) {
        try {
            isEmailError=emailFocusChange(email = email)
            isPasswordError = password ==""
            if(!isPasswordError&& !isEmailError) {
               database.orderByChild("emailId").equalTo(email.trim()).addValueEventListener(object :
                   ValueEventListener {
                   override fun onDataChange(snapshot: DataSnapshot) {
                       if(snapshot.exists()){
                           for (dataValues in snapshot.children) {
                               val data = dataValues.getValue<SignUpBO>()
                               Log.d("dataa", data.toString())
                               if(data!=null) {
                                   userDetail = data
                                   filteredData.add(data)
                               }
                           }
                           if(filteredData[0].password == password && filteredData[0].emailId ==email){
                                   navController.navigate(NavRoute.UserSelectScreen.route)
                                   {
                                       popUpTo(NavRoute.Splash.route) {
                                           inclusive = true
                                       }
                                   }
                           }
                           else
                           {
                               isInvalidDetails = true
                               Toast.makeText(context,"Account doesn't exists",Toast.LENGTH_LONG).show()
                           }
                       }
                       else
                       {
                           Toast.makeText(context,"Account doesn't exists",Toast.LENGTH_LONG).show()

                       }
                   }
                   override fun onCancelled(error: DatabaseError) {
                       Toast.makeText(context,"An Error Occured",Toast.LENGTH_LONG).show()

                   }
               })
            }
        }
        catch(e:Exception)
        {
            Log.d("LoginScreen",e.toString())
        }

    }
}