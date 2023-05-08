package com.siral.food_application.Pages.ListScreen

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.siral.food_application.BOs.DonationBO
import com.siral.food_application.BOs.DonationStatus

class ListScreenVM :ListScreenModel(){

    init {
        database = Firebase.database.reference
    }

    fun getAllDonations() {
        try {
            database.child("donation_list")
                .addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val list = mutableListOf<DonationBO>()
                            for (dataValues in snapshot.children) {
                                val data = dataValues.getValue<DonationBO>()
                                Log.d("donationdataa", data.toString())
                                if (data != null) {
                                    list.add(data)
                                }
                            }
                            val list1 =
                                list.filter { it.donationStatus == DonationStatus.Initialised.key }
                            Log.d("donationlist", list1.toString())
                            donationList.postValue(list1)
                            getReceiverList()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            donationList.postValue(emptyList())
                            loading = false
                            Log.d("errorr", "Failed to read value.", error.toException())
                        }
                    }
                )

        }
        catch(e:Exception)
        {
            Log.d("ListScreen",e.toString())
        }
    }

    fun getReceiverList()
    {
        try {
            database.child("receiver_list")
                .addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.exists()) {
                                val list = mutableListOf<DonationBO>()
                                for (dataValues in snapshot.children) {
                                    val data = dataValues.getValue<DonationBO>()
//                                    val type = object : GenericTypeIndicator<DonationBO>() {}
//                                    val data = dataValues.getValue(type)
                                    Log.d("receiverdataa", data.toString())

                                    if (data != null) {
                                        list.add(data)
                                    }
                                }
                                val list1 =
                                    list.filter { it.donationStatus == DonationStatus.Initialised.key }
                                receiverList.postValue(list1)
                            }
                            else
                            {
                                receiverList.postValue(emptyList())

                            }
                            loading = false
                        }

                        override fun onCancelled(error: DatabaseError) {
                            receiverList.postValue(emptyList())
                            loading = false
                            Log.d("errorr", "Failed to read value.", error.toException())
                        }
                    }
                )
        }
        catch(e:Exception)
        {
            Log.d("ListScreen",e.toString())
        }
    }
}