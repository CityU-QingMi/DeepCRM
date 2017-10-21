    public void testRemoveInTheFuture() {
        AnnualCalendar annualCalendar = new AnnualCalendar();
        Calendar day = Calendar.getInstance();
        
        day.set(Calendar.MONTH, Calendar.JUNE);
        day.set(Calendar.YEAR, 2005);
        day.set(Calendar.DAY_OF_MONTH, 23);
        
        annualCalendar.setDayExcluded((Calendar) day.clone(), true);

    	// Trying to remove the 23th of June
        day.set(Calendar.MONTH, Calendar.JUNE);
        day.set(Calendar.YEAR, 2008);
        day.set(Calendar.DAY_OF_MONTH, 23);
        annualCalendar.setDayExcluded((Calendar) day.clone(), false);
        
        assertTrue("The day 23 June is not expected to be excluded but it is", ! annualCalendar.isDayExcluded(day));
    }
