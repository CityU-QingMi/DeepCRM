    public DailyTimeIntervalScheduleBuilder onDaysOfTheWeek(Set<Integer> onDaysOfWeek) {
        if(onDaysOfWeek == null || onDaysOfWeek.size() == 0)
            throw new IllegalArgumentException("Days of week must be an non-empty set.");
        for (Integer day : onDaysOfWeek)
            if (!ALL_DAYS_OF_THE_WEEK.contains(day))
                throw new IllegalArgumentException("Invalid value for day of week: " + day);
                
        this.daysOfWeek = onDaysOfWeek;
        return this;
    }
