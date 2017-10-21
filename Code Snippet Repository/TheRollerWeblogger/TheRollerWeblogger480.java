    public String computeTodayMonthUrl() {
    	String url;
        if (pageLink == null) {
            // create default URL
            url = WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogCollectionURL(weblog, locale, cat, null, null, -1, false);
        } else {
            // create page URL
            url = WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogPageURL(weblog, locale, pageLink, null, cat, null, null, -1, false);
        }
    	return url;
    }
