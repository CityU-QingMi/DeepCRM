    public static Date getEndOfMonth(Date day,Calendar cal) {
        if (day == null) {
            day = new Date();
        }
        cal.setTime(day);
        
        // set time to end of day
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE,      cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        
        // set time to first day of month
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        // add one month
        cal.add(Calendar.MONTH, 1);
        
        // back up one day
        cal.add(Calendar.DAY_OF_MONTH, -1);
        
        return cal.getTime();
    }
