    public void testSubscriptionLookups() throws Exception {
        
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        
        // by id
        Subscription sub = mgr.getSubscriptionById(testSub1.getId());
        assertNotNull(sub);
        assertEquals("subFuncTest1", sub.getFeedURL());
        
        // by feed url
        sub = null;
        sub = mgr.getSubscription(testSub2.getFeedURL());
        assertNotNull(sub);
        assertEquals("subFuncTest2", sub.getFeedURL());
        
        // count
        assertEquals(2, mgr.getSubscriptionCount());
    }
