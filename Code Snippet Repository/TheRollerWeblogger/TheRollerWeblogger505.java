    public Pager getAggregationPager(int sinceDays, int length) {
        
        String pagerUrl = urlStrategy.getWeblogPageURL(weblog, 
                weblogRequest.getLocale(), pageLink, 
                null, null, null, null, 0, false);
        
        return new PlanetEntriesPager(
            urlStrategy,
            null,
            null,    
            pagerUrl,
            sinceDays,
            pageNum, 
            length);
    }
