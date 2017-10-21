    public void testDeleteEntries() throws Exception {
        
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        Subscription sub = mgr.getSubscriptionById(testSub2.getId());
        
        // make sure entries are there
        assertEquals(2, sub.getEntries().size());
        
        // purge entries
        mgr.deleteEntries(sub);
        TestUtils.endSession(true);
        
        // verify
        sub = mgr.getSubscriptionById(testSub2.getId());
        assertEquals(0, sub.getEntries().size());
    }
