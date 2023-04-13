package com.siral.food_application.Helpers.CommonUtils

import android.util.Log
import com.siral.food_application.BOs.ClientDetails
import com.siral.food_application.BOs.DonationBO
import com.siral.food_application.BOs.SignUpBO
import java.util.regex.Matcher
import java.util.regex.Pattern

object CommonUtils {

    var donationBO : ClientDetails =ClientDetails()
    var donationDetailBO : DonationBO = DonationBO()
    var userDetail : SignUpBO = SignUpBO()
    fun emailFocusChange(email:String) : Boolean
    {
        try {
            val regex =
                "^(((?!\\.)(?!.*\\.\$)(?!.*?\\.\\.)[a-z0-9.]{6,30})[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+)\$"
            val pattern: Pattern = Pattern.compile(regex)
            val matcher: Matcher = pattern.matcher(email.trim())
            return !matcher.matches()
        }
        catch(e:Exception)
        {
            Log.d("LoginScreen",e.toString())
            return false
        }

    }

}