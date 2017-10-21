    @Override
    public boolean isTimeIncluded(long timeStamp) {
        // Test the base calendar first. Only if the base calendar not already
        // excludes the time/date, continue evaluating this calendar instance.
        if (super.isTimeIncluded(timeStamp) == false) { return false; }

        java.util.Calendar day = createJavaCalendar(timeStamp);

        return !(isDayExcluded(day));
    }
