    private FeedFetcher getRomeFetcher() {
        
        FeedFetcherCache feedCache = getRomeFetcherCache();
        
        FeedFetcher feedFetcher;
        if(feedCache != null) {
            feedFetcher = new HttpURLFeedFetcher(feedCache);
        } else {
            feedFetcher = new HttpURLFeedFetcher();
        }
        
        // set options
        feedFetcher.setUsingDeltaEncoding(false);
        feedFetcher.setUserAgent("RollerPlanetAggregator");
        
        return feedFetcher;
    }
