    public WeblogCalendarModel(WeblogPageRequest pRequest, String catArgument) {
        
        this.pageRequest = pRequest;
        try {
            this.weblog = pageRequest.getWeblog();            
            if(weblog == null) {
                throw new WebloggerException("unable to lookup weblog: "+
                        pageRequest.getWeblogHandle());
            }
            pageLink = pageRequest.getWeblogPageName();            
            day = parseWeblogURLDateString(pageRequest.getWeblogDate(),
                  weblog.getTimeZoneInstance(), weblog.getLocaleInstance());
            locale = pageRequest.getLocale();
            
            // Category method argument overrides category from URL
            if (catArgument != null) {
                cat = catArgument;
            } else if (pageRequest.getWeblogCategoryName() != null) {
                cat = pageRequest.getWeblogCategoryName();
            }
            
            initDay(day);

        } catch (Exception e) {
            // some kind of error parsing the request or looking up weblog
            log.debug("ERROR: initializing calendar", e);
        }
        
    }
