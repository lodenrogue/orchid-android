package com.arkvis.orchid.pcalender.model

import android.app.Application
import com.arkvis.orchid.Day
import com.arkvis.orchid.OvulationPredictor
import com.arkvis.orchid.PeriodCalendar
import com.arkvis.orchid.PeriodPredictor
import com.arkvis.orchid.common.LocalStorageHelper
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarDataInteractor
import org.koin.core.Koin
import org.koin.java.KoinJavaComponent.inject
import java.time.LocalDate

class PeriodCalendarData : PeriodCalendarDataInteractor {
    private val localStorageHelper : LocalStorageHelper by inject()


    private var periodPredictor: PeriodPredictor = PeriodPredictor()
    private var ovulationPredictor: OvulationPredictor = OvulationPredictor()
    private var periodCalendar: PeriodCalendar = PeriodCalendar(periodPredictor, ovulationPredictor)

    override fun getPeriodDayInfo(date: LocalDate) : Day =
        periodCalendar.getDay(date)

    override fun setPeriodDay(date: LocalDate) {
//        TODO("Not yet implemented")
    }

    override fun getTemperature() {
//        TODO("Not yet implemented")
    }

    override fun setTemperature() {
//        TODO("Not yet implemented")
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
}