    protected void setUp() throws Exception {
        // setup planet
        TestUtils.setupWeblogger();
        
        // add test subscription
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        testSub = new Subscription();
        testSub.setTitle(feed_url);
        testSub.setFeedURL(feed_url);
        mgr.saveSubscription(testSub);
        WebloggerFactory.getWeblogger().flush();
    }
