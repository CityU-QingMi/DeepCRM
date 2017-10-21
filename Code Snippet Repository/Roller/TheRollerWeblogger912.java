    public void testEntryCRUD() throws Exception {
        
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        Subscription sub = mgr.getSubscriptionById(testSub.getId());
        
        SubscriptionEntry testEntry = new SubscriptionEntry();
        testEntry.setPermalink("entryBasics");
        testEntry.setTitle("entryBasics");
        testEntry.setPubTime(new java.sql.Timestamp(System.currentTimeMillis()));
        testEntry.setSubscription(sub);
        
        // add
        mgr.saveEntry(testEntry);
        TestUtils.endSession(true);
        
        // verify
        SubscriptionEntry entry = null;
        entry = mgr.getEntryById(testEntry.getId());
        assertNotNull(entry);
        assertEquals("entryBasics", entry.getPermalink());
        
        // modify
        entry.setTitle("foo");
        mgr.saveEntry(entry);
        TestUtils.endSession(true);
        
        // verify
        entry = null;
        entry = mgr.getEntryById(testEntry.getId());
        assertNotNull(entry);
        assertEquals("foo", entry.getTitle());
        
        // remove
        mgr.deleteEntry(entry);
        TestUtils.endSession(true);
        
        // verify
        entry = null;
        entry = mgr.getEntryById(testEntry.getId());
        assertNull(entry);
    }
