package nz.co.trademe.techtest.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

object DateTimeUtils {
    const val MILLIS_IN_MINUTE = 60000L
    const val MILLIS_IN_HOUR = 3600000L
    const val MILLIS_IN_DAY = 86400000L
    const val MILLIS_IN_WEEK = 604800000L

    fun getCurrentEpochTime(): Long {
        return System.currentTimeMillis()
    }

    fun epochTimeToDateTime(milliseconds: Long): String {
        val formatter = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
        return formatter.format(Date(milliseconds))
    }

    fun extractMillisecondsFromDateResponse(dateTime: String): Long {
        val dateTimeMillisecondsString =  dateTime.filter { it.isDigit() }
        return dateTimeMillisecondsString.toLong()
    }

    fun formatDateToTimeRemaining(listingClosingDate: String): String {
        val closingDateTimeEpoch = extractMillisecondsFromDateResponse(listingClosingDate)
        val currentDateTimeEpoch = getCurrentEpochTime()
        val dateDiff = abs(closingDateTimeEpoch - currentDateTimeEpoch)

        return when {
            dateDiff < MILLIS_IN_MINUTE -> "<1 min"
            dateDiff < MILLIS_IN_HOUR -> (MILLIS_IN_HOUR / dateDiff).toString() + " mins"
            dateDiff < MILLIS_IN_DAY -> (MILLIS_IN_DAY / dateDiff).toString() + " hrs"
            dateDiff < MILLIS_IN_WEEK -> (MILLIS_IN_WEEK / dateDiff).toString() + " days"
            else -> epochTimeToDateTime(dateDiff)
        }
    }
}