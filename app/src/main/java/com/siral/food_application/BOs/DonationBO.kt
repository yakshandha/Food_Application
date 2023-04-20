package com.siral.food_application.BOs

data class DonationBO(
    val donationId : String ="",
    val numberOfPersons : String="",
    val userName : String="",
    val time : String="",
    val userId : String="",
    val address : String="",
    val notes : String ="",
    val phoneNumber : String ="",
    val donationStatus : String="",
    val requestId : String =""
)

enum class DonationStatus(val key : String)
{
    Initialised("initialised"),
    Requested("requested"),
    Accepted("accepted")
}
