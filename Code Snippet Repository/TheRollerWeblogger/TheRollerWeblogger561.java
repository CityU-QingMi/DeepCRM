    public WeblogEntriesDayPager(
            URLStrategy        strat,
            Weblog             weblog,
            String             locale,
            String             pageLink,
            String             entryAnchor,
            String             dateString,
            String             catName,
            List               tags,
            int                page) {
        
        super(strat, weblog, locale, pageLink, entryAnchor, dateString, catName, tags, page);
        
        dayFormat = new SimpleDateFormat(
            messageUtils.getString("weblogEntriesPager.day.dateFormat"));
        
        getEntries();
        
        day = parseDate(dateString);
        
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(day);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        nextDay = cal.getTime();
        if (nextDay.after(getToday())) {
            nextDay = null;
        }
        
        cal.setTime(day);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        prevDay = cal.getTime();
        Date weblogInitialDate = weblog.getDateCreated() != null ? weblog.getDateCreated() : new Date(0);
        if (DateUtil.getEndOfDay(prevDay,cal).before(weblogInitialDate)) {
            prevDay = null;
        }
    }
