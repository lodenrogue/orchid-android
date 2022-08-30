package com.arkvis.orchid.pcalender.interfaces

import com.arkvis.orchid.Day
import com.arkvis.orchid.Temperature
import java.time.LocalDate

interface PeriodCalendarDataInteractor {
    fun getPeriodDayInfo(date: LocalDate) : Day?
    fun deletePeriod(date: LocalDate)
    fun setPeriodDay(date: LocalDate?)


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