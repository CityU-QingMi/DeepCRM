    public void testHitCountCRUD() throws Exception {
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        WeblogHitCount testCount = new WeblogHitCount();
        testCount.setWeblog(testWeblog);
        testCount.setDailyHits(10);
        
        // create
        mgr.saveHitCount(testCount);
        String id = testCount.getId();
        TestUtils.endSession(true);
        
        // make sure it was created
        WeblogHitCount hitCount;
        hitCount = mgr.getHitCount(id);
        assertNotNull(hitCount);
        assertEquals(testCount, hitCount);
        assertEquals(10, hitCount.getDailyHits());
        
        // update
        hitCount.setDailyHits(25);
        mgr.saveHitCount(hitCount);
        TestUtils.endSession(true);
        
        // make sure it was updated
        hitCount = mgr.getHitCount(id);
        assertNotNull(hitCount);
        assertEquals(testCount, hitCount);
        assertEquals(25, hitCount.getDailyHits());
        
        // delete
        mgr.removeHitCount(hitCount);
        TestUtils.endSession(true);
        
        // make sure it was deleted
        hitCount = mgr.getHitCount(id);
        assertNull(hitCount);
    }
