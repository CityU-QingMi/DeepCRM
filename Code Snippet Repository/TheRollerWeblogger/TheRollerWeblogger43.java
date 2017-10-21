    public static Date getStartOfHour(Date day, Calendar cal) {
        if (day == null) {
            day = new Date();
        }
        cal.setTime(day);
        cal.set(Calendar.MINUTE,      cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        return cal.getTime();
    }
