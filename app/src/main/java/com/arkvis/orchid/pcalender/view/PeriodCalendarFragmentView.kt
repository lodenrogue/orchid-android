package com.arkvis.orchid.pcalender.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.arkvis.orchid.R
import com.arkvis.orchid.databinding.PeriodCalendarFragmentBinding
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarViewInteractor
import com.arkvis.orchid.pcalender.presenter.PeriodCalendarPresenter


class PeriodCalendarFragmentView : Fragment(), PeriodCalendarViewInteractor {

    private var periodCalendarPresenter = PeriodCalendarPresenter(this)
    private var _binding: PeriodCalendarFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PeriodCalendarFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        periodCalendarPresenter.getPeriodDates()
        testing()

//        binding.orchidDateSelector.setOnClickListener {
//            findNavController().navigate(R.id.action_PeriodCalendarFragmentView_to_SecondFragment)
//        }
    }

//    @Throws(ParseException::class)
//    private fun highlightDates(scheduleList: List<Schedule>) {
//        for (position in scheduleList.indices) {
//            val schedule: Schedule = scheduleList[position]
//            if (schedule.getSessionStatus().equals("Incomplete")) {
//                val color: Int = R.color.colorPrimaryDark
//                val decorator = CurrentDayDecorator(Date(schedule.getScheduledDate()), color)
//                binding.periodCalendar.addDec(decorator)
//            }
//        }
//    }
//
//    private fun initViews() {
//        calendarView = v.findViewById(R.id.calendarView)
//    }
//

    @RequiresApi(Build.VERSION_CODES.O)
    private fun testing() {
//        val calendar = PeriodCalendar(PeriodPredictor(), OvulationPredictor())
//        val periodDate: LocalDate = now()
//        calendar.addPeriod(periodDate, Flow.LIGHT)
//        calendar.addPeriod(periodDate.plusDays(1), Flow.LIGHT)
//        calendar.addPeriod(periodDate.plusDays(2), Flow.LIGHT)
//        calendar.addPeriod(periodDate.plusDays(3), Flow.LIGHT)
//
//        for (date in calendar.nextPeriodWindow.dates) {
//            binding.periodCalendar.date = date.getLong(ChronoField.DAY_OF_MONTH)
//        }

        val builder :  MaterialDatePicker.Builder<Long> = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText(getString(R.string.orchid_day_selector_title))
        val datePicker : MaterialDatePicker<Long> = builder.build()

        binding.orchidDateSelector.setOnClickListener {
            datePicker.show(parentFragmentManager, datePicker.toString())
        }
        datePicker.addOnPositiveButtonClickListener {
            Toast.makeText(
                requireContext(),
                "Selected Date : " + datePicker.headerText,
                Toast.LENGTH_SHORT
            ).show()
            binding.periodCalendar.date =
                periodCalendarPresenter.getFormatedSelectedDate(it)

        }
        binding.periodCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showPeriodOnCalendar() {
//        TODO("Not yet implemented")
    }
}