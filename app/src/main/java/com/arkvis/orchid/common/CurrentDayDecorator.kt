package com.arkvis.orchid.common


//class CurrentDayDecorator : DayViewDecorator {
//    private var color: Int = 0
//    private lateinit var day: CalendarDay
//
//    fun CurrentDayDecorator(date : Date, color: Int) {
//        this.color = color;
//        this.day = CalendarDay.from(date);
//    }
//    @Override
//    fun  shouldDecorate( day: CalendarDay): Boolean {
//        if (this.day.equals(day)){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    fun decorate( view: DayViewFacade) {
//        val span: LineBackgroundSpan = CustomSpan(color, xOffsets.get(spanType))
//        view.addSpan(span)
//    }
//}