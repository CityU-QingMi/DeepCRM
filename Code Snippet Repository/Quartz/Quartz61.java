    public static Date futureDate(int interval, IntervalUnit unit) {
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.setLenient(true);
        
        c.add(translate(unit), interval);

        return c.getTime();
    }
