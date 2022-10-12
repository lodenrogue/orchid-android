package com.arkvis.orchid.pcalender.interfaces

import com.arkvis.orchid.Day
import com.arkvis.orchid.Flow
import java.time.LocalDate

interface PeriodCalendarPresenterInteractor {
    fun getOrchidInfoToday(): Day?
    fun getOrchidInfoForDate(date: LocalDate): Day?
    fun clearPeriodToday()
    fun setPeriodToday()
    fun setPeriod(date: LocalDate)
    fun setPeriod(date: LocalDate, flow: Flow)
}