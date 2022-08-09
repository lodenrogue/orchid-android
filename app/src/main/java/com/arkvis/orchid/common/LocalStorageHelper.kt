package com.arkvis.orchid.common

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.arkvis.orchid.PeriodCalendar
import com.google.gson.Gson


const val ORCHID_CALENDAR = "orchid calendar"

class LocalStorageHelper(activity: Activity) {
    private val sharedPref: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)

    fun getPeriodCalendar(): PeriodCalendar? {
        val periodCalendarString = sharedPref.getString(ORCHID_CALENDAR, "")
        return if (!periodCalendarString.equals("")) {
            Gson().fromJson(periodCalendarString, PeriodCalendar::class.java)
        } else {
            null
        }
    }

    fun setPeriodCalendar(periodCalendar: PeriodCalendar) {
        sharedPref.edit().apply {
            val gson = Gson()
            val json = gson.toJson(periodCalendar)
            putString(ORCHID_CALENDAR, json)
            apply()
        }
    }

    fun clearValues() {
        sharedPref.edit().clear().apply()
    }

}