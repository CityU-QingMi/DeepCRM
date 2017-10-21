    public TimeZone getTimeZone() {
        
        if(cronEx != null) {
            return cronEx.getTimeZone();
        }
        
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        return timeZone;
    }
