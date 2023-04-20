package com.siral.food_application.Pages.FoodFormScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.siral.food_application.BOs.DonationBO
import com.siral.food_application.BOs.DonationStatus
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.userDetail
import com.siral.food_application.NavigationHelpers.NavRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormScreenVM:FormScreenModel() {
    init {
        database = Firebase.database.getReference("donation_list")
    }
    fun submitClicked(navController: NavHostController, userType: String?, context: Context) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                isPersonsError = numberOfPersons == ""
                isTimeError = time == ""
                isAddressError = address == ""
                isNameError = userName ==""
                if (!isPersonsError && !isTimeError && !isAddressError) {
                    val donationId: String? = database.push().key
                    if (donationId != null) {
                        val donation = DonationBO(
                            donationId,
                            numberOfPersons,
                            userName,
                            time,
                            userDetail.userId,
                            address,
                            notes,
                            phoneNumber,
                            DonationStatus.Initialised.key
                        )
                        database.child(donationId).setValue(donation)
                            .addOnSuccessListener {
                                navController.navigate(NavRoute.ListScreen.route)
                                {
                                    popUpTo("foodFormScreen/Donate") {
                                        inclusive = true
                                    }
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
        catch (e: Exception)
        {
            Log.d("FormScreen",e.toString())
        }
    }
//
//    fun nameUnFocusChange() {
//
//    }
}