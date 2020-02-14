package id.cpp.kotlinutilities.utils

import android.annotation.SuppressLint
import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils {

    fun dateNow(): String {
        val formatted: String

        formatted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.YYY")
            current.format(formatter)
        } else {
            val date = getCurrentDateTime()
            date.toString("dd.MM.YYY")
        }
        return formatted
    }

    fun prodDate(proddate: String): String {
        val formatted: String

        formatted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("YYY-MM-dd")
            current.format(formatter)
        } else {
            val date = getCurrentDateTime()
            date.toString("YYY-MM-dd")
        }
        return formatted
    }

    @SuppressLint("SimpleDateFormat")
    fun getProdDateFrom(time: String): String? {
        var reformattedDate: String? = null
        val inputFormat = SimpleDateFormat("dd.MM.yyyy")
        val outputFormat = SimpleDateFormat("yyyy-MM-dd")
        try {
            reformattedDate = outputFormat.format(inputFormat.parse(time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return reformattedDate
    }

    @SuppressLint("SimpleDateFormat")
    fun changeDateFormat(time: String): String? {
        var reformattedDate: String? = null
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("dd.MM.yyyy")
        try {
            reformattedDate = outputFormat.format(inputFormat.parse(time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return reformattedDate
    }

    fun getUpdatedOn(): String {
        val formatted: String

        formatted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("YYY-MM-dd HH:mm:ss")
            current.format(formatter)
        } else {
            val date = getCurrentDateTime()
            date.toString("YYY-MM-dd HH:mm:ss")
        }
        return formatted
    }

    fun getDayLength(year: Int, month: Int): Int {
        val maxDay: Int
        val yearMonthObject: YearMonth
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            yearMonthObject =
                YearMonth.of(year, month)
            yearMonthObject.lengthOfMonth()
        } else {
            val calendar = Calendar.getInstance()
            calendar.set(year, month, 1)
            maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            maxDay
        }
    }

    fun dateNowDashboard(): String {
        val formatted: String

        formatted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.YYY, EEEE", Locale("id"))

            current.format(formatter)
        } else {
            val date = getCurrentDateTime()
            date.toString("dd.MM.yyyy EEEE")
        }
        return formatted
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun getDateFromMillis(milliSeconds: Long): String {
        // Create a DateFormatter object for displaying date in specified format.
        val dateFormat = "dd.MM.yyyy"
        val formatter = SimpleDateFormat(dateFormat, Locale("id"))

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    fun getTimeInMillis(day: Int, month: Int, year: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day)
        return calendar.timeInMillis
    }

    fun getTimeInMillisFromTimestamp(timestamp: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id"))
        try {
            val mDate = sdf.parse(timestamp)
            val timeInMilliseconds = mDate.time
            println("Date in milli :: $timeInMilliseconds")
            return timeInMilliseconds
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

}