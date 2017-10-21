    public String computeUrl(Date day, boolean monthURL, boolean alwaysURL) {
        String url = null;
        // get the 8 char YYYYMMDD datestring for day
        String dateString = (String) monthMap.get(day);
        if (dateString == null && !alwaysURL) {
            return null;
        }
        else if (dateString == null && !monthURL) {
        	dateString = format8chars(day,getCalendar());
        } else if (dateString == null) {
            dateString = format6chars(day,getCalendar());
        }
        try {
            if (pageLink == null) {
                // create date URL
                url = WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogCollectionURL(weblog, locale, cat, dateString, null, -1, false);
            } else {
                // create page URL
                url = WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogPageURL(weblog, locale, pageLink, null, cat, dateString, null, -1, false);
            }
        } catch (Exception e) {
            log.error("ERROR: creating URL",e);
        }
        return url;
    }
