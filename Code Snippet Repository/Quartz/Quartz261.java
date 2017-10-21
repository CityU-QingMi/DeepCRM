    @Override
    public boolean isTimeIncluded(long timeStamp) {
        if (excludeAll == true) {
            return false;
        }

        // Test the base calendar first. Only if the base calendar not already
        // excludes the time/date, continue evaluating this calendar instance.
        if (super.isTimeIncluded(timeStamp) == false) { return false; }

        java.util.Calendar cl = createJavaCalendar(timeStamp);
        int wday = cl.get(java.util.Calendar.DAY_OF_WEEK);

        return !(isDayExcluded(wday));
    }
