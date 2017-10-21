    public void testUpdateSubscription() throws Exception {
        
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        Subscription sub = mgr.getSubscriptionById(testSub.getId());
        
        // update the subscription
        FeedUpdater updater = new SingleThreadedFeedUpdater();
        updater.updateSubscription(sub);
        TestUtils.endSession(true);
        
        // verify the results
        sub = mgr.getSubscription(feed_url);
        assertNotNull(sub);
        assertEquals(feed_url, sub.getFeedURL());
        assertEquals("http://rollerweblogger.org/roller/", sub.getSiteURL());
        assertEquals("Blogging Roller", sub.getTitle());
        assertNotNull(sub.getLastUpdated());
        assertTrue(sub.getEntries().size() > 0);
    }
