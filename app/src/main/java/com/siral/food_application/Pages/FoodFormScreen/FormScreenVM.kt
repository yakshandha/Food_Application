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
    private val donateUser = "Donate"
    val requestUser = "Receive"
    init {
        database = Firebase.database.getReference("donation_list")
        receiverDB = Firebase.database.getReference("receiver_list")

    }
    fun submitClicked(navController: NavHostController, userType: String?, context: Context) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                isPersonsError = numberOfPersons == ""
                isTimeError = time == ""
                isAddressError = address == ""
                isNameError = userName == ""
                if (!isPersonsError && !isTimeError && !isAddressError) {
                    val donationId: String? = if(userType==donateUser)
                        database.push().key
                    else
                        receiverDB.push().key
                    if (donationId != null) {
                        if(userType==donateUser) {
                            val donation = DonationBO(
                                donationId = donationId,
                                numberOfPersons = numberOfPersons,
                                userName = userName,
                                time = time,
                                userId = userDetail.userId,
                                address = address,
                                notes = notes,
                                phoneNumber = phoneNumber,
                                donationStatus = DonationStatus.Initialised.key,
                            )
                            database.child(donationId).setValue(donation)
                                .addOnSuccessListener {
                                    navController.navigate(NavRoute.ListScreen.route)
                                    {
                                        popUpTo("foodFormScreen/{FormType}") {
                                            inclusive = true
                                        }
                                    }
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                                }
                        }
                        else
                        {
                            val donation = DonationBO(
                                numberOfPersons = numberOfPersons,
                                userName = userName,
                                time = time,
                                userId = userDetail.userId,
                                address = address,
                                notes = notes,
                                phoneNumber = phoneNumber,
                                donationStatus = DonationStatus.Initialised.key,
                                requestId = donationId
                            )
                            receiverDB.child(donationId).setValue(donation)
                                .addOnSuccessListener {
                                    navController.navigate(NavRoute.ListScreen.route)
                                    {
                                        popUpTo("foodFormScreen/{FormType}") {
                                            inclusive = true
                                        }
                                    }
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                                }
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
}