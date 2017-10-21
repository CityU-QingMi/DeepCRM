    protected void loadWeblogEntries(Date startDate, Date endDate, String catName) {
        try {
            WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
            wesc.setWeblog(weblog);
            wesc.setStartDate(startDate);
            wesc.setEndDate(endDate);
            wesc.setCatName(catName);
            wesc.setStatus(PubStatus.PUBLISHED);
            wesc.setLocale(locale);
            monthMap = mgr.getWeblogEntryStringMap(wesc);
        } catch (WebloggerException e) {
            log.error(e);
            monthMap = new HashMap<Date, String>();
        }
    }
