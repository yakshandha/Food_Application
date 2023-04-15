package com.siral.food_application.BOs

data class DonationBO(
    val donationId : String ="",
    val numberOfPersons : String="",
    val userName : String="",
    val time : String="",
    val userId : String="",
    val address : String="",
    val notes : String?="",
    val donationStatus : String=""
)
