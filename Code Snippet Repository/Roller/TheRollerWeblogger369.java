    public Date getNextAllowedRun(int interval) {
        
        Date previousRun = getLastRun();
        if(previousRun == null) {
            return new Date(0);
        }
        
        // calculate next run time
        Calendar cal = Calendar.getInstance();
        cal.setTime(previousRun);
        cal.add(Calendar.MINUTE, interval);
        
        return cal.getTime();
    }
