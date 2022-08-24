package com.arkvis.orchid.pcalender.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.arkvis.orchid.Day
import com.arkvis.orchid.Flow
import com.arkvis.orchid.R
import com.arkvis.orchid.databinding.PeriodCalendarFragmentBinding
import com.arkvis.orchid.pcalender.interfaces.PeriodCalendarFragmentViewInteractor
import com.arkvis.orchid.pcalender.presenter.PeriodCalendarPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class PeriodCalendarFragmentView : Fragment(), PeriodCalendarFragmentViewInteractor {

    private val periodCalendarPresenter = PeriodCalendarPresenter(this)
    private var _binding: PeriodCalendarFragmentBinding? = null
    private var currentSelectedDay: Day? = periodCalendarPresenter.getOrchidInfoToday()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalendarControl()
    }

    private fun initCalendarControl() {
        initCalendarControlListener()
        initCalendarControlData()
    }

    private fun initCalendarControlListener() {
        binding.orchidDayToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.ovulationToggle.isChecked = false
                binding.fertilityToggle.isChecked = false
            }
        }
        binding.ovulationToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.orchidDayToggle.isChecked = false
                binding.fertilityToggle.isChecked = false
            }
        }
        binding.fertilityToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.orchidDayToggle.isChecked = false
                binding.ovulationToggle.isChecked = false
            }
        }

        binding.periodCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val currentSelectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            currentSelectedDay = periodCalendarPresenter.getOrchidInfoForDate(currentSelectedDate)
            updateControls()
            updateDateDisplayed(currentSelectedDate)
        }

        binding.fab.setOnClickListener { view ->
            var buttonDrawable: Drawable = view.background
            buttonDrawable = DrawableCompat.wrap(buttonDrawable)
            DrawableCompat.setTint(
                buttonDrawable,
                currentSelectedDay?.let {
                    currentSelectedDay = null
                    (view as FloatingActionButton).setImageResource(R.drawable.check_saved)
                    ContextCompat.getColor(requireContext(), R.color.green)
                } ?: run {
                    periodCalendarPresenter.setPeriodToday()
                    (view as FloatingActionButton).setImageResource(android.R.drawable.ic_delete)
                    currentSelectedDay = periodCalendarPresenter.getOrchidInfoToday()
                    ContextCompat.getColor(requireContext(), R.color.red)
                })
            view.background = buttonDrawable
        }
    }

    private fun initCalendarControlData() {
        updateDateDisplayed(LocalDate.now())

        //Mock Values
        binding.orchidDayToggle.isChecked = true
        binding.flowValue.text = Flow.Medium.name
//        periodCalendarPresenter.setPeriod(LocalDate.now())
        //End of Mock Values

        updateControls()
    }

    private fun updateControls() {
        currentSelectedDay?.period?.let {
            binding.orchidDayToggle.isChecked = true
            binding.flowValue.text = it.flow?.name ?: ""
            binding.mucusValue.text = getText(R.string.feature_upcoming)
        }
        currentSelectedDay?.temperature?.let {
            binding.temperatureValue.text =
                resources.getString(R.string.temperature_formatter, it.value, it.metric.name)
        }
        if (binding.orchidDayToggle.isChecked) {
            binding.ovulationToggle.isChecked = false
            binding.fertilityToggle.isChecked = false
        }
    }

    private fun updateDateDisplayed(date: LocalDate) {
        val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        binding.currentDate.text = date.format(formatter);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showPeriodOnCalendar() {
//        TODO("Not yet implemented")
    }
}