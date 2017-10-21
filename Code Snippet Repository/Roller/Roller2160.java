    public String formatDate(Date d, String fmt, TimeZone tzOverride) {
        
        if (d == null || fmt == null) {
            return fmt;
        }
        
        SimpleDateFormat format = new SimpleDateFormat(fmt, weblog.getLocaleInstance());
        if(tzOverride != null) {
            format.setTimeZone(tzOverride);
        }
        
        return format.format(d);
    }
