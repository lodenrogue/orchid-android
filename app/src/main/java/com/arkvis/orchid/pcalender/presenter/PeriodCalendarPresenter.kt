package com.arkvis.orchid.pcalender.presenter

import com.arkvis.orchid.pcalender.model.PeriodCalendarData
import com.arkvis.orchid.pcalender.view.PeriodCalendarFragmentView
import java.util.*

class PeriodCalendarPresenter(
    private val periodCalendarView: PeriodCalendarFragmentView) {
    private val periodCalendarData = PeriodCalendarData()

    fun getPeriodDates() = periodCalendarData.getPeriodDays()

    fun getFormatedSelectedDate(selectedDate: Long) : Long{
        val timeZoneUTC = TimeZone.getDefault()
        // It will be negative, so that's the -1
        val offsetFromUTC = (timeZoneUTC.getOffset(Date().time) * -1).toLong()
        return selectedDate + offsetFromUTC
    }

}