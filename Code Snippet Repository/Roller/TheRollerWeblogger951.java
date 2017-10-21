    public void testHitCountLookups() throws Exception {
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        WeblogHitCount testCount = new WeblogHitCount();
        testCount.setWeblog(testWeblog);
        testCount.setDailyHits(10);
        
        // create
        mgr.saveHitCount(testCount);
        String id = testCount.getId();
        TestUtils.endSession(true);
        
        // test lookup by id
        WeblogHitCount hitCount;
        hitCount = mgr.getHitCount(id);
        assertNotNull(hitCount);
        assertEquals(testCount, hitCount);
        assertEquals(10, hitCount.getDailyHits());
        
        // test lookup by weblog
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        hitCount = mgr.getHitCountByWeblog(testWeblog);
        assertNotNull(hitCount);
        assertEquals(testCount, hitCount);
        assertEquals(10, hitCount.getDailyHits());
        
        // delete
        mgr.removeHitCount(hitCount);
        TestUtils.endSession(true);
        
        // make sure it was deleted
        hitCount = mgr.getHitCount(id);
        assertNull(hitCount);
    }
