package com.siral.food_application.BOs

data class DonationRequestBO(
    val donationBO: DonationBO = DonationBO(),
    val requesterDetail : SignUpBO = SignUpBO()
)
