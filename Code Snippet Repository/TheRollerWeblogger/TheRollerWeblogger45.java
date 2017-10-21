    public static Date getEndOfMinute(Date day, Calendar cal) {
        if (day == null || cal == null) {
            return day;
        }
        
        cal.setTime(day);
        cal.set(Calendar.SECOND,      cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return cal.getTime();
    }
