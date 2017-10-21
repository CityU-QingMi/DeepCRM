    public static Date getStartOfMonth(Date day, Calendar cal) {
        if (day == null) {
            day = new Date();
        }
        cal.setTime(day);
        
        // set time to start of day
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE,      cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        
        // set time to first day of month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        return cal.getTime();
    }
