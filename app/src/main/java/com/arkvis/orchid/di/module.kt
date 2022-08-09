package com.arkvis.orchid.di

import com.arkvis.orchid.common.LocalStorageHelper
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarDataInteractor
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarPresenterInteractor
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarFragmentViewInteractor
import com.arkvis.orchid.pcalender.model.PeriodCalendarData
import com.arkvis.orchid.pcalender.presenter.PeriodCalendarPresenter
import com.arkvis.orchid.pcalender.view.PeriodCalendarFragmentView
import org.koin.dsl.module

val sharedPrefModule = module {
    single { LocalStorageHelper(get()) }
}
val orchidModule = module {
    single<PeriodCalendarFragmentViewInteractor> { PeriodCalendarFragmentView() }
    single<PeriodCalendarPresenterInteractor> { PeriodCalendarPresenter(get()) }
    single<PeriodCalendarDataInteractor> { PeriodCalendarData() }
}