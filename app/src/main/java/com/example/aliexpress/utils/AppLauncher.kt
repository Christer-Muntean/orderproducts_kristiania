package com.example.aliexpress.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri

class AppLauncher {

    companion object {
        fun sendSMS(tel: String, message: String, activity: Activity) {
            val uri = Uri.parse("smsto:$tel")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", message)
            activity.startActivity(intent)
        }

        fun sendEmail(email: String, subject: String, message: String, activity: Activity) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto: $email")
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            activity.startActivity(intent)
        }
    }

}