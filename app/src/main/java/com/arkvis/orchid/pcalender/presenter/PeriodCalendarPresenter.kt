package com.arkvis.orchid.pcalender.presenter

import com.arkvis.orchid.Day
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarPresenterInteractor
import com.arkvis.orchid.pcalender.model.PeriodCalendarData
import com.arkvis.orchid.pcalender.view.PeriodCalendarFragmentView
import org.koin.core.component.inject
import java.time.LocalDate
import java.util.*

class PeriodCalendarPresenter(periodCalendarView: PeriodCalendarFragmentView)  :
    PeriodCalendarPresenterInteractor {

    private val periodCalendarData : PeriodCalendarData by inject()

//    fun getPeriodDates() = periodCalendarData.getPeriodDays()

    fun getFormatedSelectedDate(selectedDate: Long) : Long{
        val timeZoneUTC = TimeZone.getDefault()
        // It will be negative, so that's the -1
        val offsetFromUTC = (timeZoneUTC.getOffset(Date().time) * -1).toLong()
        return selectedDate + offsetFromUTC
    }

    override fun getOrchidInfoToday() : Day? =
        periodCalendarData?.getPeriodDayInfo(LocalDate.now())

}