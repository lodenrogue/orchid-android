package com.arkvis.orchid.pcalender.model

import com.arkvis.orchid.*
import com.arkvis.orchid.BaseApp.Companion.localStorage
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarDataInteractor
import java.time.LocalDate

class PeriodCalendarData : PeriodCalendarDataInteractor {

    private var periodPredictor: PeriodPredictor =
        localStorage?.periodPredictor ?: PeriodPredictor()
    private var ovulationPredictor: OvulationPredictor =
        localStorage?.ovulationPredictor ?: OvulationPredictor()
    private var periodCalendar: PeriodCalendar =
        localStorage?.periodCalendar ?: PeriodCalendar(periodPredictor, ovulationPredictor)

    override fun getPeriodDayInfo(date: LocalDate): Day? =
        periodCalendar.getDay(date)

    override fun setPeriodDay(date: LocalDate, flow: Flow) {
        periodCalendar.addPeriod(date, flow)
        onUpdate()
    }

    override fun setPeriodDay(date: LocalDate) {
        periodCalendar.addPeriod(date)
        onUpdate()
    }

    override fun clearPeriod(date: LocalDate) {
        periodCalendar.clearPeriod(date)
        onUpdate()
    }

    override fun getTemperature(): Temperature? {
        //        TODO("Not yet implemented")
        return null
    }

    override fun setTemperature(date: LocalDate, temperature: Temperature) {
        periodCalendar.addTemperature(date, temperature)
        onUpdate()
    }

    override fun getCervicalMucus() {
//        TODO("Not yet implemented")
    }

    override fun setCervicalMucus() {
//        TODO("Not yet implemented")
    }

    override fun getCervixInfo() {
//        TODO("Not yet implemented")
    }

    override fun setCervixInfo() {
//        TODO("Not yet implemented")
    }

    override fun getDesire() {
//        TODO("Not yet implemented")
    }

    override fun setDesire() {
//        TODO("Not yet implemented")
    }

    override fun getSexualIntercourseOccurrence() {
//        TODO("Not yet implemented")
    }

    override fun setSexualIntercourseOccurrence() {
//        TODO("Not yet implemented")
    }

    private fun onUpdate() {
        localStorage?.periodCalendar = periodCalendar
    }
}