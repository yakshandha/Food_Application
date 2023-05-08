package com.siral.food_application.Pages.FoodFormScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.siral.food_application.Helpers.CommonUtils.CommonUtils.userDetail

open class FormScreenModel :ViewModel() {
    var userName by mutableStateOf(userDetail.userName)
    var numberOfPersons by mutableStateOf("")
    var time by mutableStateOf("")
    var address by mutableStateOf(userDetail.address)
    var notes by mutableStateOf("")
    var phoneNumber by mutableStateOf(userDetail.phoneNumber)

    var isPersonsError by mutableStateOf(false)
    var isTimeError by mutableStateOf(false)
    var isAddressError by mutableStateOf(false)
    var isNameError by mutableStateOf(false)

    lateinit var database: DatabaseReference
    lateinit var receiverDB : DatabaseReference

}