package com.arkvis.orchid.pcalender.interfaces

import com.arkvis.orchid.Day
import com.arkvis.orchid.Flow
import com.arkvis.orchid.Temperature
import java.time.LocalDate

interface PeriodCalendarDataInteractor {
    fun getPeriodDayInfo(date: LocalDate) : Day?
    fun clearPeriod(date: LocalDate)
    fun setPeriodDay(date: LocalDate)
    fun setPeriodDay(date: LocalDate, flow: Flow)


    //Todo at a later time
    fun getTemperature(): Temperature?
    fun setTemperature(date: LocalDate , temperature: Temperature)

    fun getCervicalMucus()
    fun setCervicalMucus()

    fun getCervixInfo()
    fun setCervixInfo()

    fun getDesire()
    fun setDesire()

    fun getSexualIntercourseOccurrence()
    fun setSexualIntercourseOccurrence()

}