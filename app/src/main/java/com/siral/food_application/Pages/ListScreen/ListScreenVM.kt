package com.siral.food_application.Pages.ListScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.siral.food_application.BOs.DonationBO
import com.siral.food_application.BOs.DonationStatus

class ListScreenVM :ListScreenModel(){

    init {
        database = Firebase.database.getReference("donation_list")
    }

    fun getAllDonations() {
        database
            .addValueEventListener(
                object : ValueEventListener
                {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var list = mutableListOf<DonationBO>()
                        for (dataValues in snapshot.children) {
                            val data = dataValues.getValue<DonationBO>()
                            Log.d("dataa", data.toString())
                            if(data!=null) {
                                list.add(data)
                            }
                        }
                       val list1 = list.filter { it.donationStatus == DonationStatus.Initialised.key }
                        Log.d("wholelist",list1.toString())
                        donationList.postValue(list1)
                        loading = false
                    }

                    override fun onCancelled(error: DatabaseError) {
                        donationList.postValue(emptyList())
                        loading = false
                        Log.d("errorr", "Failed to read value.", error.toException())
                    }
                }
            )
    }
}