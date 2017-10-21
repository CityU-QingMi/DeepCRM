    public void testIncrementHitCount() throws Exception {
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        WeblogHitCount testCount = new WeblogHitCount();
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        testCount.setWeblog(testWeblog);
        testCount.setDailyHits(10);
        
        // create
        mgr.saveHitCount(testCount);
        String id = testCount.getId();
        TestUtils.endSession(true);
        
        // make sure it was created
        WeblogHitCount hitCount;
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        hitCount = mgr.getHitCountByWeblog(testWeblog);
        assertNotNull(hitCount);
        assertEquals(10, hitCount.getDailyHits());
        
        // increment
        mgr.incrementHitCount(testWeblog, 25);
        TestUtils.endSession(true);
        
        // make sure it was incremented properly
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        hitCount = mgr.getHitCountByWeblog(testWeblog);
        assertNotNull(hitCount);
        assertEquals(35, hitCount.getDailyHits());
        
        // delete
        mgr.removeHitCount(hitCount);
        TestUtils.endSession(true);
        
        // make sure it was deleted
        hitCount = mgr.getHitCount(id);
        assertNull(hitCount);
    }
