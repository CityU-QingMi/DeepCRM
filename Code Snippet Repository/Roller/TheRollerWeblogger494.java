    private String showWeblogEntryCalendar(WeblogWrapper websiteWrapper, String catArgument, boolean big) {
        
        if ("nil".equals(catArgument)) {
            catArgument = null;
        }
        String ret = null;
        try {
            org.apache.roller.weblogger.ui.core.tags.calendar.CalendarModel model = null;
            if (big) {
                model = new BigWeblogCalendarModel(pageRequest, catArgument);
            } else {
                model = new WeblogCalendarModel(pageRequest, catArgument);
            }
            
            // save model in JSP page context so CalendarTag can find it
            pageContext.setAttribute("calendarModel", model);
            
            CalendarTag calTag = new CalendarTag();
            calTag.setPageContext(pageContext);
            calTag.setName("calendar");
            calTag.setModel("calendarModel");
            calTag.setLocale(websiteWrapper.getLocaleInstance());
            if (big) {
                calTag.setClassSuffix("Big");
            }
            ret = calTag.emit();
        } catch (Exception e) {
            log.error("ERROR: initializing calendar tag",e);
        }
        return ret;
    }
