package com.textsdev.cleanweatherapp_mvvm.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

fun Int.toDay(): String {
    return unixTimeToGMTDate().getDayOfWeek()
}

@SuppressLint("SimpleDateFormat")
fun Int.unixTimeToGMTDate(): Date {
    // Create a SimpleDateFormat instance with GMT timezone
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    sdf.timeZone = TimeZone.getTimeZone("GMT")

    // Convert Unix timestamp to milliseconds and create a Date object
    return Date(this * 1000L)
}

fun Date.getDayOfWeek(): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    // Convert day of week integer to string representation
    return when (dayOfWeek) {
        Calendar.SUNDAY -> "Sunday"
        Calendar.MONDAY -> "Monday"
        Calendar.TUESDAY -> "Tuesday"
        Calendar.WEDNESDAY -> "Wednesday"
        Calendar.THURSDAY -> "Thursday"
        Calendar.FRIDAY -> "Friday"
        Calendar.SATURDAY -> "Saturday"
        else -> throw IllegalArgumentException("Invalid day of week")
    }
}

fun showSnackBar(text: String, view: View, function: () -> Unit) {
    Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        .setTextColor(Color.WHITE)
        .setBackgroundTint(Color.BLACK)
        .setActionTextColor(Color.RED)
        .setAction("RETRY") {
            function()
        }
        .show()
}