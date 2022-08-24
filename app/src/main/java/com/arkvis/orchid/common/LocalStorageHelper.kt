package com.arkvis.orchid.common

import android.content.Context
import android.content.SharedPreferences
import com.arkvis.orchid.OvulationPredictor
import com.arkvis.orchid.PeriodCalendar
import com.arkvis.orchid.PeriodPredictor
import com.google.gson.Gson

private const val ORCHID_CALENDAR = "orchid calendar"
private const val ORCHID_PREDICTOR = "orchid predictor"
private const val ORCHID_OVULATION_PREDICTOR = "orchid ovulation predictor"
private const val ORCHID_SP = "orchid sharedpreferences"

class LocalStorageHelper(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(ORCHID_SP, Context.MODE_PRIVATE)

    var periodCalendar: PeriodCalendar
        get() {
            val periodCalendarString = sharedPref.getString(ORCHID_CALENDAR, "")
            return if (periodCalendarString.equals("")) {
                val tempPCalendar = PeriodCalendar(periodPredictor, ovulationPredictor)
                savePeriodCalendar(tempPCalendar)
                tempPCalendar
            } else {
                Gson().fromJson(periodCalendarString, PeriodCalendar::class.java)
            }
        }
        set(value) {
            savePeriodCalendar(value)
        }

    var periodPredictor: PeriodPredictor
        get() {
            val periodPredictor = sharedPref.getString(ORCHID_PREDICTOR, "")
            return if (periodPredictor.equals("")) {
                val tempPredictor = PeriodPredictor()
                savePeriodPredictor(tempPredictor)
                tempPredictor
            } else {
                Gson().fromJson(periodPredictor, PeriodPredictor::class.java)
            }
        }
        set(value) {
            savePeriodPredictor(value)
        }
    var ovulationPredictor: OvulationPredictor
        get() {
            val periodPredictor = sharedPref.getString(ORCHID_OVULATION_PREDICTOR, "")
            return if (periodPredictor.equals("")) {
                val tempOvPredictor = OvulationPredictor()
                saveOvulationPredictor(tempOvPredictor)
                tempOvPredictor
            } else {
                Gson().fromJson(periodPredictor, OvulationPredictor::class.java)
            }
        }
        set(value) {
            saveOvulationPredictor(value)
        }

    private fun savePeriodCalendar(value : PeriodCalendar){
        sharedPref.edit().apply {
            val gson = Gson()
            val json = gson.toJson(value)
            putString(ORCHID_CALENDAR, json)
            apply()
        }
    }

    private fun savePeriodPredictor(value : PeriodPredictor){
        sharedPref.edit().apply {
            val gson = Gson()
            val json = gson.toJson(value)
            putString(ORCHID_PREDICTOR, json)
            apply()
        }
    }

    private fun saveOvulationPredictor(value : OvulationPredictor){
        sharedPref.edit().apply {
            val gson = Gson()
            val json = gson.toJson(value)
            putString(ORCHID_OVULATION_PREDICTOR, json)
            apply()
        }
    }

//    fun getPeriodCalendar(): PeriodCalendar? {
//        val periodCalendarString = sharedPref.getString(ORCHID_CALENDAR, "")
//        return if (!periodCalendarString.equals("")) {
//            Gson().fromJson(periodCalendarString, PeriodCalendar::class.java)
//        } else {
//            null
//        }
//    }
//
//    fun setPeriodCalendar(periodCalendar: PeriodCalendar) {
//        sharedPref.edit().apply {
//            val gson = Gson()
//            val json = gson.toJson(periodCalendar)
//            putString(ORCHID_CALENDAR, json)
//            apply()
//        }
//    }

    fun clearValues() {
        sharedPref.edit().clear().apply()
    }

}