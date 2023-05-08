package com.siral.food_application.Pages.DonationDetailScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.siral.food_application.BOs.DonationStatus
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.donationDetailBO
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.userDetail
import com.siral.food_application.NavigationHelpers.NavRoute

class DonationDetailVM : DonationDetailModel() {
    init {
        database = Firebase.database.getReference("donation_list/${donationDetailBO.donationId}")
    }
    var recevedDb = Firebase.database.getReference("receiver_list/${donationDetailBO.requestId}")
    fun submitClicked(navController: NavHostController, context: Context, isReceiver: Boolean) {
        try {
            if(!isReceiver) {
                val updates = mapOf(
                    "donationStatus" to DonationStatus.Requested.key,
                    "requestId" to userDetail.userId
                )
                database.updateChildren(updates)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Requested", Toast.LENGTH_SHORT).show()
                        navController.navigate(NavRoute.ProfileScreen.route) {
                            popUpTo(NavRoute.DonationDetailScreen.route) {
                                inclusive = true
                            }
                        }
                    }
            }
            else{
                val updates = mapOf(
                    "${donationDetailBO.donationId}/donationStatus" to DonationStatus.Donated.key,
                    "${donationDetailBO.donationId}/donationId" to userDetail.userId
                )
                recevedDb.updateChildren(updates)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Donated", Toast.LENGTH_SHORT).show()
                        navController.navigate(NavRoute.ProfileScreen.route) {
                            popUpTo(NavRoute.DonationDetailScreen.route) {
                                inclusive = true
                            }
                        }
                    }
            }
        }
        catch (e: Exception)
        {
            Log.d("DonationDetail",e.toString())
        }
    }
}