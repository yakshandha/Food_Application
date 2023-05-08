package com.siral.food_application.Pages.UserProfileFlow.RequestedListScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.siral.food_application.BOs.DonationRequestBO

open class RequestListModel : ViewModel() {
    lateinit var database: DatabaseReference
    lateinit var userDatabase : DatabaseReference
    var requestList = MutableLiveData<List<DonationRequestBO>>()
    val list2 = mutableListOf<DonationRequestBO>()
    var loading by mutableStateOf(true)
    var noRequest by mutableStateOf(false)

}