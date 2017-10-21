    public static TimeOfDay hourAndMinuteFromDate(Date dateTime, TimeZone tz) {
        if (dateTime == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        if(tz != null)
            cal.setTimeZone(tz);
        
        return new TimeOfDay(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
    }
