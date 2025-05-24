package com.nightfury.presentation.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class DateUtils {
    companion object {
        fun getYesterdayAndNowDates(): Pair<String, String> {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            formatter.timeZone = TimeZone.getTimeZone("UTC")

            // Today
            val today = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            val to = formatter.format(today.time)

            // Yesterday
            val yesterday = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            yesterday.add(Calendar.DATE, -1)
            val from = formatter.format(yesterday.time)

            return from to to
        }

        fun formatDate(isoString: String): String {
            return try {
                val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                inputFormatter.timeZone = TimeZone.getTimeZone("UTC")

                val date = inputFormatter.parse(isoString)
                val outputFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.US)
                outputFormatter.format(date!!)
            } catch (e: Exception) {
                isoString
            }
        }
    }
}