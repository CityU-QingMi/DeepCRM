    public void setDaysExcluded(boolean[] days) {
        if (days == null) {
            throw new IllegalArgumentException("The days parameter cannot be null.");
        }

        if (days.length < MAX_DAYS_IN_MONTH) {
            throw new IllegalArgumentException(
                "The days parameter must have a length of at least " + MAX_DAYS_IN_MONTH + " elements.");
        }

        excludeDays = days;
        excludeAll = areAllDaysExcluded();
    }
