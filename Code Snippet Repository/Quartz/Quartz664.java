    public void testDaysExcludedOverTime() {
        AnnualCalendar annualCalendar = new AnnualCalendar();
        Calendar day = Calendar.getInstance();
        
        day.set(Calendar.MONTH, Calendar.JUNE);
        day.set(Calendar.YEAR, 2005);
        day.set(Calendar.DAY_OF_MONTH, 23);
        
        annualCalendar.setDayExcluded((Calendar) day.clone(), true);
        
    	day.set(Calendar.YEAR, 2008);
    	day.set(Calendar.MONTH, Calendar.FEBRUARY);
    	day.set(Calendar.DAY_OF_MONTH, 1);
    	annualCalendar.setDayExcluded((Calendar) day.clone(), true);
 
    	assertTrue("The day 1 February is expected to be excluded but it is not", annualCalendar.isDayExcluded(day));    	
    }
