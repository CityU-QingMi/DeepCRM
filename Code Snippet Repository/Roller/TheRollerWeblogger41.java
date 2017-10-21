    public static Date parseWeblogURLDateString(String dateString, TimeZone tz, Locale locale) {
        
        Date ret = new Date();
        SimpleDateFormat char8DateFormat = DateUtil.get8charDateFormat();
        SimpleDateFormat char6DateFormat = DateUtil.get6charDateFormat();
        
        if (dateString != null
                && dateString.length()==8
                && StringUtils.isNumeric(dateString) ) {
            ParsePosition pos = new ParsePosition(0);
            ret = char8DateFormat.parse(dateString, pos);
            
            // make sure the requested date is not in the future
            Date today = null;
            Calendar todayCal = Calendar.getInstance();
            todayCal = Calendar.getInstance(tz, locale);
            todayCal.setTime(new Date());
            today = todayCal.getTime();
            if(ret.after(today)) {
                ret = today;
            }
            
        } else if(dateString != null
                && dateString.length()==6
                && StringUtils.isNumeric(dateString)) {
            ParsePosition pos = new ParsePosition(0);
            ret = char6DateFormat.parse(dateString, pos);
            
            // make sure the requested date is not in the future
            Calendar todayCal = Calendar.getInstance();
            todayCal = Calendar.getInstance(tz, locale);
            todayCal.setTime(new Date());
            Date today = todayCal.getTime();
            if(ret.after(today)) {
                ret = today;
            }
        }
        
        return ret;
    }
