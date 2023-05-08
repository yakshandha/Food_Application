package com.siral.food_application.Pages.UserProfileFlow.DonationRequests

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.siral.food_application.BOs.DonationBO
import com.siral.food_application.BOs.DonationRequestBO
import com.siral.food_application.BOs.DonationStatus
import com.siral.food_application.BOs.SignUpBO
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.userDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DonationRequestListVM : DonationRequestListModel() {

    init {
        database = Firebase.database.getReference("donation_list")
        userDatabase = Firebase.database.getReference("users")
    }

    fun getRequestedDonations() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                var list = mutableListOf<DonationBO>()
                Log.d("jijo", userDetail.userId)
                val query = database.orderByChild("userId").equalTo(userDetail.userId)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.exists()) {
                                noRequest = false
                                for (snap in snapshot.children) {
                                    val data = snap.getValue<DonationBO>()
                                    Log.d("dataa", data.toString())
                                    if (data != null) {
                                        list.add(data)
                                    }
                                }
                                list = list.filter { it.donationStatus == DonationStatus.Requested.key } as MutableList<DonationBO>
                                getData(list)
                                loading = false
                            }
                            else
                            {
                                loading = false
                                noRequest = true
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            // Handle the error as needed
                            Log.e(
                                "DonationRequestScreen",
                                "Error searching for nodes with value",
                                error.toException()
                            )
                        }
                    })
            }
        }
        catch(e : Exception)
        {
            Log.d("DonationRequestScreen",e.toString())
        }
    }
 fun getData(list: MutableList<DonationBO>)
 {
     try{
         if (list.size >0) {
         list.forEach {
             userDatabase.child(it.requestId)
                 .addListenerForSingleValueEvent(object : ValueEventListener {
                     override fun onDataChange(dataSnapshot: DataSnapshot) {
                         // Handle the retrieved data here
                         val data = dataSnapshot.getValue<SignUpBO>()
                         if (data != null) {
                             val data2 = DonationRequestBO(it, data)
                             list2.add(data2)
                         }
                         donationRequestList.postValue(list2)
                         loading = false
                         Log.d("donationrequest", list2.toString())

                     }

                     override fun onCancelled(error: DatabaseError) {
                         // Handle the error as needed
                         Log.e(
                             "DonationRequestScreen",
                             "Error searching for nodes with value",
                             error.toException()
                         )
                     }
                 });
         }
     }
     }
     catch(e : Exception)
     {
         Log.d("DonationRequestScreen",e.toString())
     }
 }

    fun changeRequestStatus(request: DonationRequestBO, navController: NavHostController) {
        try {
            val updates = mapOf(
                "${request.donationBO.donationId}/donationStatus" to DonationStatus.Accepted.key)
            database.updateChildren(updates)
                .addOnSuccessListener {
                    navController.popBackStack()
                }
        }
        catch(e : Exception)
        {
            Log.d("DonationRequestScreen",e.toString())
        }
    }
}