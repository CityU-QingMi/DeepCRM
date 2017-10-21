    public Pager getFeedPager(String feedURL, int length) {
        
        String pagerUrl = urlStrategy.getWeblogPageURL(weblog, 
                weblogRequest.getLocale(), pageLink, 
                null, null, null, null, 0, false);
        
        return new PlanetEntriesPager(
            urlStrategy,
            feedURL,
            null,
            pagerUrl,
            -1,
            pageNum, 
            length);
    }
