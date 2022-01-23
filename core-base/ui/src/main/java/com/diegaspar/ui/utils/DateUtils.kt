package com.diegaspar.ui.utils

import java.time.*
import java.time.format.DateTimeFormatter

const val MonthDayYearPattern = "MMM dd yyyy" //Apr 10 2021
const val MonthDayPattern = "MMM dd" //Apr 10

fun dateStringToMillis(date: String): Long {
    val formatter = DateTimeFormatter.ofPattern(MonthDayYearPattern)
    val parsed: LocalDate = LocalDate.parse(date, formatter)
    return ZonedDateTime.of(
        parsed, LocalTime.MIDNIGHT,
        ZoneId.systemDefault()
    ).toInstant().toEpochMilli()
}

fun millisToDatePatternString(millis: Long): String {
    val formatter = DateTimeFormatter.ofPattern(MonthDayPattern)
    val instant = Instant.ofEpochMilli(millis)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(date).orEmpty()
}

