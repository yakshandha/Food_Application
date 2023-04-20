package com.siral.food_application.Pages.DonationDetailScreen

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference

open class DonationDetailModel : ViewModel() {
    lateinit var database: DatabaseReference
}