    public WeblogEntriesMonthPager(
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
        
        monthFormat = new SimpleDateFormat(
            messageUtils.getString("weblogEntriesPager.month.dateFormat"));
        
        getEntries();
        
        month = parseDate(dateString);
        
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(month);
        cal.add(Calendar.MONTH, 1);
        nextMonth = cal.getTime();
        if (nextMonth.after(getToday())) {
            nextMonth = null;
        }
        
        cal.setTime(month);
        cal.add(Calendar.MONTH, -1);
        prevMonth = cal.getTime();
        Date endOfPrevMonth = DateUtil.getEndOfMonth(prevMonth,cal) ;
        Date weblogInitialDate = weblog.getDateCreated() != null ? weblog.getDateCreated() : new Date(0);
        if (endOfPrevMonth.before(weblogInitialDate)) {
            prevMonth = null;
        }
    }
