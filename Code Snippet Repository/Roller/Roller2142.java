    public Pager getWeblogEntriesPager(int sinceDays, int length) {
        
        String pagerUrl;
        
        if (feedRequest != null) {
            pagerUrl = urlStrategy.getWeblogFeedURL(weblog, 
                    weblogRequest.getLocale(), feedRequest.getType(),
                    feedRequest.getFormat(), feedRequest.getWeblogCategoryName(), null,
                    feedRequest.getTags(), feedRequest.isExcerpts(), true);
        } else {        
            pagerUrl = urlStrategy.getWeblogPageURL(weblog, 
                weblogRequest.getLocale(), pageLink, 
                null, null, null, tags, 0, false);
        }
        
        return new WeblogEntriesListPager(
            urlStrategy,
            pagerUrl, null, null, null,
            tags,
            weblogRequest.getLocale(),
            sinceDays,
            pageNum, 
            length);
    }
