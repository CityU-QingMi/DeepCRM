    public Date getTimeOfDayForDate(Date dateTime) {
        if (dateTime == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.clear(Calendar.MILLISECOND);
        return cal.getTime();
    }
