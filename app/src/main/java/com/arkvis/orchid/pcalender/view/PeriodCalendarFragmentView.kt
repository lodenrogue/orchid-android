package com.arkvis.orchid.pcalender.view


import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.arkvis.orchid.Day
import com.arkvis.orchid.Flow
import com.arkvis.orchid.R
import com.arkvis.orchid.common.FabState
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
    private var currentSelectedDate: LocalDate = LocalDate.now()
    private var adapter: ArrayAdapter<Flow>? = null
    private var fabState = FabState.NOACTION
    private var isUpdatingFlowName = false

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = PeriodCalendarFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initCalendarControl()
    }

    override fun onResume() {
        super.onResume()
        initCalendarControl()
        binding.fab.background?.mutate()?.setTint( ContextCompat.getColor(requireContext(), R.color.green))
    }

    private fun initCalendarControl() {
        initCalendarControlListener()
        initSpinner()
        initCalendarControlData()
    }

    private fun initCalendarControlListener() {
        binding.orchidDayToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.ovulationToggle.isChecked = false
                binding.fertilityToggle.isChecked = false
            }
            binding.spinnerFlowValue.isVisible = isChecked
            binding.flowLabel.isVisible = isChecked
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

        binding.spinnerFlowValue.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long,
                ) {
                    currentSelectedDay?.period?.flow =
                        Flow.valueOf(parent?.getItemAtPosition(position).toString())
                    if (isUpdatingFlowName) {
                        isUpdatingFlowName = false
                    } else {
                        fabNoAction(binding.fab)
                    }
                }
            }

        binding.periodCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            currentSelectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            currentSelectedDay = periodCalendarPresenter.getOrchidInfoForDate(currentSelectedDate)

            updateControls()
            updateDateDisplayed(currentSelectedDate)
        }

        binding.fab.setOnClickListener { updateFab() }
    }

    private fun updateFab() {
        binding.fab.let { fab: FloatingActionButton ->
            when (fabState) {
                FabState.CLEARED, FabState.NOACTION -> {
                    fabSaveAction(fab)
                }
                FabState.SAVED -> {
                    fabClearAction(fab)
                }
            }
        }
    }

    private fun fabSaveAction(fab: FloatingActionButton) {
        fabState = FabState.SAVED

        if (binding.orchidDayToggle.isChecked) {
            periodCalendarPresenter.setPeriod(
                currentSelectedDate,
                currentSelectedDay?.period?.flow ?: Flow.MEDIUM)
        }
        currentSelectedDay = periodCalendarPresenter.getOrchidInfoForDate(currentSelectedDate)
        setFab(fab, R.drawable.check_saved, R.color.green)
    }

    private fun fabClearAction(fab: FloatingActionButton) {
        fabState = FabState.CLEARED
        periodCalendarPresenter.clearPeriodToday()
        currentSelectedDay = null
        setFab(fab, android.R.drawable.ic_delete, R.color.red)
    }

    private fun fabNoAction(fab: FloatingActionButton) {
        fabState = FabState.NOACTION
        setFab(fab, android.R.drawable.ic_input_add, R.color.grey)
    }

    private fun setFab(fab: FloatingActionButton, drawable: Int, color: Int) {
        fab.setImageResource(drawable)
        val backgroundTintColor = ContextCompat.getColor(requireContext(), color)
        binding.fab.backgroundTintList = ColorStateList.valueOf(backgroundTintColor)
    }

    private fun initCalendarControlData() {
        updateDateDisplayed(LocalDate.now())
        updateControls()
    }

    private fun initSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            Flow.values())
        binding.spinnerFlowValue.adapter = adapter
        binding.spinnerFlowValue.setSelection(
            adapter.getPosition(Flow.MEDIUM))
        this.adapter = adapter
    }

    private fun updateControls() {
        setFlowSpinnerByName(currentSelectedDay?.period?.flow?.name)
        currentSelectedDay?.period?.let {
            binding.orchidDayToggle.isChecked = true
            binding.mucusValue.text = getText(R.string.feature_upcoming)
            fabSaveAction(binding.fab)
        } ?: run {
            binding.orchidDayToggle.isChecked = false
            fabNoAction(binding.fab)
        }

        currentSelectedDay?.temperature?.let {
            binding.temperatureValue.text =
                resources.getString(R.string.temperature_formatter, it.value, it.metric.name)
        }
    }

    private fun setFlowSpinnerByName(nFlowName: String?) {
        val flowName = nFlowName ?: Flow.MEDIUM.name
        isUpdatingFlowName = binding.spinnerFlowValue.selectedItem.toString() != flowName
        adapter?.let { adapt ->
            binding.spinnerFlowValue.setSelection(
                adapt.getPosition(Flow.valueOf(flowName)))
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