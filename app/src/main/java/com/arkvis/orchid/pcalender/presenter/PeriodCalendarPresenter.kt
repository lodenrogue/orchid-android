package com.arkvis.orchid.pcalender.presenter

import com.arkvis.orchid.Day
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarPresenterInteractor
import com.arkvis.orchid.pcalender.model.PeriodCalendarData
import com.arkvis.orchid.pcalender.view.PeriodCalendarFragmentView
import java.time.LocalDate
import java.util.*

class PeriodCalendarPresenter(periodCalendarView: PeriodCalendarFragmentView)  :
    PeriodCalendarPresenterInteractor {

    private val periodCalendarData = PeriodCalendarData()

    fun getFormatedSelectedDate(selectedDate: Long) : Long{
        val timeZoneUTC = TimeZone.getDefault()
        // It will be negative, so that's the -1
        val offsetFromUTC = (timeZoneUTC.getOffset(Date().time) * -1).toLong()
        return selectedDate + offsetFromUTC
    }

    override fun getOrchidInfoToday() : Day? =
        periodCalendarData.getPeriodDayInfo(LocalDate.now())

    override fun getOrchidInfoForDate(date: LocalDate) : Day? =
        periodCalendarData.getPeriodDayInfo(date)

    override fun deletePeriodToday(){
        periodCalendarData.deletePeriod(LocalDate.now())
    }
    override fun setPeriodToday() {
        periodCalendarData.setPeriodDay(LocalDate.now())
    }

    override fun setPeriod(date: LocalDate?) {
        periodCalendarData.setPeriodDay(date)
    }

}