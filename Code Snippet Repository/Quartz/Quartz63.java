    public static Date tomorrowAt(int hour, int minute, int second) {
        validateSecond(second);
        validateMinute(minute);
        validateHour(hour);

        Date date = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setLenient(true);

        // advance one day
        c.add(Calendar.DAY_OF_YEAR, 1);
        
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }
