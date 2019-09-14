package com.patricksales.testwhitecard.core.extensions

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_FORMAT_1 = "dd/MMMM/yyyy"

private fun String.getTimeStampFromDateTime(mDateFormat: String): Long {
    val dateFormat = SimpleDateFormat(mDateFormat)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = dateFormat.parse(this)
    return date.time
}

private fun Long.getDateStringFromTimeStamp(mDateFormat: String): String {
    val dateFormat = SimpleDateFormat(mDateFormat)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = Date(this)
    return dateFormat.format(dateTime)
}

fun String.getDateFormatted() : String{
    return (this.getTimeStampFromDateTime(DATE_FORMAT)).getDateStringFromTimeStamp(DATE_FORMAT_1)
}

