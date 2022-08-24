package com.arkvis.orchid.pcalender.interfaces

import com.arkvis.orchid.Day
import java.time.LocalDate

interface PeriodCalendarPresenterInteractor {
    fun getOrchidInfoToday(): Day?
    fun getOrchidInfoForDate(date: LocalDate): Day?
    fun setPeriodToday()
    fun setPeriod(date: LocalDate?)
}