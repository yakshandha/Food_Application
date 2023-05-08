package com.siral.food_application.Pages.ListScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.siral.food_application.BOs.DonationBO

open class ListScreenModel: ViewModel() {

    lateinit var database: DatabaseReference
    var donationList = MutableLiveData<List<DonationBO>>()
    var receiverList = MutableLiveData<List<DonationBO>>()
    var loading by mutableStateOf(true)

}