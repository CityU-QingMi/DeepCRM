    public static CronScheduleBuilder atHourAndMinuteOnGivenDaysOfWeek(
            int hour, int minute, Integer... daysOfWeek) {
        if (daysOfWeek == null || daysOfWeek.length == 0)
            throw new IllegalArgumentException(
                    "You must specify at least one day of week.");
        for (int dayOfWeek : daysOfWeek)
            DateBuilder.validateDayOfWeek(dayOfWeek);
        DateBuilder.validateHour(hour);
        DateBuilder.validateMinute(minute);

        String cronExpression = String.format("0 %d %d ? * %d", minute, hour,
                daysOfWeek[0]);

        for (int i = 1; i < daysOfWeek.length; i++)
            cronExpression = cronExpression + "," + daysOfWeek[i];

        return cronScheduleNoParseException(cronExpression);
    }
