package com.example.journeynotes.ui.extension

import java.text.SimpleDateFormat
import java.util.*


fun Long.convertToDateFormat(): String {
    val simpleDateFormat = SimpleDateFormat("dd MM yyyy HH:mm", Locale(Locale.getDefault().language))
    return simpleDateFormat.format(Date(this))
}