    @Override
    protected Object getTargetObject() {
        AnnualCalendar c = new AnnualCalendar();
        
        c.setDescription("description");
        
        Calendar cal = Calendar.getInstance(EST_TIME_ZONE, Locale.US); 
        cal.clear();
        cal.set(2005, Calendar.JANUARY, 20, 10, 5, 15);
        
        c.setDayExcluded(cal, true);
        
        return c;
    }
