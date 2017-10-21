    public void testSubscriptionCRUD() throws Exception {
        
        // setup planet
        TestUtils.setupWeblogger();

        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        
        Subscription testSub = new Subscription();
        testSub.setFeedURL("test_title");
        testSub.setTitle("test_title");
        
        Subscription sub = mgr.getSubscription(testSub.getFeedURL());
        assertNull(sub);
        
        // add
        mgr.saveSubscription(testSub);
        TestUtils.endSession(true);
        
        // verify
        sub = null;
        sub = mgr.getSubscriptionById(testSub.getId());
        assertNotNull(sub);
        assertEquals("test_title", sub.getFeedURL());
        
        // modify
        sub.setTitle("foo");
        mgr.saveSubscription(sub);
        TestUtils.endSession(true);
        
        // verify
        sub = null;
        sub = mgr.getSubscriptionById(testSub.getId());
        assertNotNull(sub);
        assertEquals("foo", sub.getTitle());
        
        // remove
        mgr.deleteSubscription(sub);
        TestUtils.endSession(true);
        
        // verify
        sub = null;
        sub = mgr.getSubscriptionById(testSub.getId());
        assertNull(sub);
        
    }
