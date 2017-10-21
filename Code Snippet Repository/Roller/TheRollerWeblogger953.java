    public void testHotWeblogs() throws Exception {
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        testUser = TestUtils.getManagedUser(testUser);
        Weblog blog1 = TestUtils.setupWeblog("hitCntHotTest1", testUser);
        Weblog blog2 = TestUtils.setupWeblog("hitCntHotTest2", testUser);
        Weblog blog3 = TestUtils.setupWeblog("hitCntHotTest3", testUser);
        
        WeblogHitCount cnt1 = TestUtils.setupHitCount(blog1, 10);
        WeblogHitCount cnt2 = TestUtils.setupHitCount(blog2, 20);
        WeblogHitCount cnt3 = TestUtils.setupHitCount(blog3, 30);
        
        TestUtils.endSession(true);
        
        // make sure data was properly initialized
        WeblogHitCount testCount;
        testCount = mgr.getHitCount(cnt1.getId());
        assertEquals(10, testCount.getDailyHits());
        testCount = mgr.getHitCount(cnt2.getId());
        assertEquals(20, testCount.getDailyHits());
        testCount = mgr.getHitCount(cnt3.getId());
        assertEquals(30, testCount.getDailyHits());
        
        // get hot weblogs
        List hotBlogs = mgr.getHotWeblogs(1, 0, 5);
        assertNotNull(hotBlogs);
        assertEquals(3, hotBlogs.size());
        
        // also check ordering and values
        WeblogHitCount hitCount;
        Iterator it = hotBlogs.iterator();
        for (int i=3; it.hasNext(); i--) {
            hitCount = (WeblogHitCount) it.next();
            assertEquals(i*10, hitCount.getDailyHits());
        }
        
        // cleanup
        TestUtils.teardownHitCount(cnt1.getId());
        TestUtils.teardownHitCount(cnt2.getId());
        TestUtils.teardownHitCount(cnt3.getId());
        TestUtils.teardownWeblog(blog1.getId());
        TestUtils.teardownWeblog(blog2.getId());
        TestUtils.teardownWeblog(blog3.getId());
    }
