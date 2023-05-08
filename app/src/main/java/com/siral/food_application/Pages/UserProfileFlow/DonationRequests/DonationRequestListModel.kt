package com.siral.food_application.Pages.UserProfileFlow.DonationRequests

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.siral.food_application.BOs.DonationBO
import com.siral.food_application.BOs.DonationRequestBO

open class DonationRequestListModel : ViewModel() {
    lateinit var database: DatabaseReference
    lateinit var userDatabase : DatabaseReference
    var donationRequestList = MutableLiveData<List<DonationRequestBO>>()
    val list2 = mutableListOf<DonationRequestBO>()
    var loading by mutableStateOf(true)
    var noRequest by mutableStateOf(false)

}