    protected Date parseDate(String dateString) {
        Date ret = null;
        SimpleDateFormat char8DateFormat = DateUtil.get8charDateFormat();
        SimpleDateFormat char6DateFormat = DateUtil.get6charDateFormat();
        Calendar cal = Calendar.getInstance(
                weblog.getTimeZoneInstance(), weblog.getLocaleInstance());
        if (   dateString!=null
                && dateString.length()==8
                && StringUtils.isNumeric(dateString) ) {
        	char8DateFormat.setCalendar(cal);
            ParsePosition pos = new ParsePosition(0);
            ret = char8DateFormat.parse( dateString, pos );
            
            // make sure the requested date is not in the future
            Date today = getToday();
            if (ret.after(today)) {
                ret = today;
            }
        }
        if (   dateString!=null
                && dateString.length()==6
                && StringUtils.isNumeric(dateString) ) {
        	char6DateFormat.setCalendar(cal);
            ParsePosition pos = new ParsePosition(0);
            ret = char6DateFormat.parse( dateString, pos );
            
            // make sure the requested date is not in the future
            Date today = getToday();
            if (ret.after(today)) {
                ret = today;
            }
        }
        return ret;
    }
