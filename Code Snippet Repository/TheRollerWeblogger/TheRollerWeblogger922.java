    public void testFetchFeed() throws FetcherException {
        try {
            FeedFetcher feedFetcher = WebloggerFactory.getWeblogger().getFeedFetcher();
            
            // fetch feed
            Subscription sub = feedFetcher.fetchSubscription(feed_url);
            assertNotNull(sub);
            assertEquals(feed_url, sub.getFeedURL());
            assertEquals("http://rollerweblogger.org/roller/", sub.getSiteURL());
            assertEquals("Blogging Roller", sub.getTitle());
            assertNotNull(sub.getLastUpdated());
            assertTrue(sub.getEntries().size() > 0);

        } catch (FetcherException ex) {
            log.error("Error fetching feed", ex);
            throw ex;
        }
    }
