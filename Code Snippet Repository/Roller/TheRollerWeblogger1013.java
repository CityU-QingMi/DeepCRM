    public void testFetchSubscription() throws Exception {
        try {
            FeedFetcher feedFetcher = WebloggerFactory.getWeblogger().getFeedFetcher();

            // first fetch non-conditionally so we know we should get a Sub
            Subscription sub = feedFetcher.fetchSubscription(feed_url);
            assertNotNull(sub);
            assertEquals(feed_url, sub.getFeedURL());
            assertNotNull(sub.getLastUpdated());

            // now do a conditional fetch and we should get back null
            Subscription updatedSub = feedFetcher.fetchSubscription(feed_url, sub.getLastUpdated());
            assertNull(updatedSub);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
