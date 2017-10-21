    public static Date parseWeblogURLDateString(String dateString, TimeZone tz, Locale locale) {
        
        Date ret = new Date();
        Calendar cal = Calendar.getInstance(tz,locale);
        
        if (dateString != null
                && dateString.length()==8
                && StringUtils.isNumeric(dateString) ) {
            SimpleDateFormat char8DateFormat = DateUtil.get8charDateFormat();
            char8DateFormat.setCalendar(cal);
            ParsePosition pos = new ParsePosition(0);
            ret = char8DateFormat.parse(dateString, pos);

            // make sure the requested date is not in the future
            // Date is always ms offset from epoch in UTC, by no means of timezone.
            Date today = new Date();
            if(ret.after(today)) {
                ret = today;
            }
            
        } else if(dateString != null
                && dateString.length()==6
                && StringUtils.isNumeric(dateString)) {
            SimpleDateFormat char6DateFormat = DateUtil.get6charDateFormat();
            char6DateFormat.setCalendar(cal);
            ParsePosition pos = new ParsePosition(0);
            ret = char6DateFormat.parse(dateString, pos);
            
            // make sure the requested date is not in the future
            Date today = new Date();
            if(ret.after(today)) {
                ret = today;
            }
        }
        
        return ret;
    }
