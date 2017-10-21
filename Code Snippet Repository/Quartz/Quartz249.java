    public DailyCalendar(org.quartz.Calendar baseCalendar,
                         int rangeStartingHourOfDay,
                         int rangeStartingMinute,
                         int rangeStartingSecond,
                         int rangeStartingMillis,
                         int rangeEndingHourOfDay,
                         int rangeEndingMinute,
                         int rangeEndingSecond,
                         int rangeEndingMillis) {
        super(baseCalendar);
        setTimeRange(rangeStartingHourOfDay,
                     rangeStartingMinute,
                     rangeStartingSecond,
                     rangeStartingMillis,
                     rangeEndingHourOfDay,
                     rangeEndingMinute,
                     rangeEndingSecond,
                     rangeEndingMillis);
    }
