    public Pager getUsersByLetterPager(String letter, int sinceDays, int length) {
        
        String pagerUrl;
        if (feedRequest != null) {
            pagerUrl = urlStrategy.getWeblogFeedURL(weblog, 
                    weblogRequest.getLocale(), feedRequest.getType(),
                    feedRequest.getFormat(), null, null, null, feedRequest.isExcerpts(), true);
        } else {        
            pagerUrl = urlStrategy.getWeblogPageURL(weblog, 
                weblogRequest.getLocale(), pageLink, 
                null, null, null, null, 0, false);
        }        
        
        if(letter != null && StringUtils.isEmpty(letter)) {
            letter = null;
        }
        
        return new UsersPager(
            urlStrategy,
            pagerUrl,
            letter,
            weblogRequest.getLocale(),
            sinceDays,
            pageNum, 
            length);
    }      
