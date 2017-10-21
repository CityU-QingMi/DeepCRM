    public void setTimeRange(Calendar rangeStartingCalendar,
                              Calendar rangeEndingCalendar) {
        setTimeRange(
                rangeStartingCalendar.get(Calendar.HOUR_OF_DAY),
                rangeStartingCalendar.get(Calendar.MINUTE),
                rangeStartingCalendar.get(Calendar.SECOND),
                rangeStartingCalendar.get(Calendar.MILLISECOND),
                rangeEndingCalendar.get(Calendar.HOUR_OF_DAY),
                rangeEndingCalendar.get(Calendar.MINUTE),
                rangeEndingCalendar.get(Calendar.SECOND),
                rangeEndingCalendar.get(Calendar.MILLISECOND));
    }
