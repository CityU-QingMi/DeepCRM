    public boolean willFireOn(Calendar test, boolean dayOnly) {

        test = (Calendar) test.clone();
        
        test.set(Calendar.MILLISECOND, 0); // don't compare millis.
        
        if(dayOnly) {
            test.set(Calendar.HOUR_OF_DAY, 0); 
            test.set(Calendar.MINUTE, 0); 
            test.set(Calendar.SECOND, 0); 
        }
        
        Date testTime = test.getTime();
        
        Date fta = getFireTimeAfter(new Date(test.getTime().getTime() - 1000));
        
        if(fta == null)
            return false;

        Calendar p = Calendar.getInstance(test.getTimeZone());
        p.setTime(fta);
        
        int year = p.get(Calendar.YEAR);
        int month = p.get(Calendar.MONTH);
        int day = p.get(Calendar.DATE);
        
        if(dayOnly) {
            return (year == test.get(Calendar.YEAR) 
                    && month == test.get(Calendar.MONTH) 
                    && day == test.get(Calendar.DATE));
        }
        
        while(fta.before(testTime)) {
            fta = getFireTimeAfter(fta);
        }

        return fta.equals(testTime);
    }
