    public Date build() {
        Calendar cal;

        if(tz != null && lc != null)
            cal = Calendar.getInstance(tz, lc);
        else if(tz != null)
            cal = Calendar.getInstance(tz);
        else if(lc != null)
            cal = Calendar.getInstance(lc);
        else 
          cal = Calendar.getInstance();
        
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        
        return cal.getTime();
    }
