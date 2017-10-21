    public String computeUrl(Date day, boolean nextPrevMonthURL, boolean alwaysURL) {
        String url = null;
        // get the 8 char YYYYMMDD datestring for day, returns null
        // if no weblog entry on that day
        String dateString = null;
        List entries = (List)monthMap.get( day );
        if ( entries != null && day != null ) {
            WeblogEntry entry = (WeblogEntry)entries.get(0);
            dateString = starDateFormat.format(entry.getPubTime());
        }
        if (dateString == null && !alwaysURL) {
            return null;
        }
        else if (dateString == null && !nextPrevMonthURL) {
            dateString = DateUtil.format8chars(day);
        } else if (dateString == null) {
            dateString = DateUtil.format6chars(day);
        }
        try {
            if (nextPrevMonthURL && pageLink != null) { 
                // next/prev month URLs point to current page
                url = WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogPageURL(weblog, locale, pageLink, null, cat, dateString, null, -1, false);
            } else { 
                // all other URLs point back to main weblog page
                url = WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogCollectionURL(weblog, locale, cat, dateString, null, -1, false);
            }
        } catch (Exception e) {
            mLogger.error("ERROR: creating URL",e);
        }
        return url;
    }
