    public Pager getAggregationPager(String groupHandle, int sinceDays, int length) {
        
        String pagerUrl = urlStrategy.getWeblogPageURL(weblog, 
                weblogRequest.getLocale(), pageLink, 
                null, null, null, null, 0, false);
        
        return new PlanetEntriesPager(
            urlStrategy,
            null,
            groupHandle,
            pagerUrl,
            sinceDays,
            pageNum, 
            length);
    }
