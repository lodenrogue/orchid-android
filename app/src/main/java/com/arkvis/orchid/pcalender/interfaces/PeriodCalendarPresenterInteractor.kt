package com.arkvis.orchid.pcalender.interfaces

import com.arkvis.orchid.Day
import org.koin.core.component.KoinComponent

interface PeriodCalendarPresenterInteractor : KoinComponent {
    fun getOrchidInfoToday() : Day?
}