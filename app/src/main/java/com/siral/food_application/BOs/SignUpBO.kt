package com.siral.food_application.BOs

data class SignUpBO(
    var userId : String ="",
    var emailId: String ="",
    var password:String="",
    var phoneNumber: String="",
    var userName: String="",
    var address: String="",
    var donation_ids: List<String>?= emptyList(),
    var request_ids:List<String>? = emptyList()
)
