    public void testEntryLookups() throws Exception {
        
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        
        // by id
        SubscriptionEntry entry = mgr.getEntryById(testEntry1.getId());
        assertNotNull(entry);
        assertEquals("entryFuncTestEntry1", entry.getPermalink());
        
        // by subscription
        Subscription sub = mgr.getSubscriptionById(testSub2.getId());
        assertEquals(2, sub.getEntries().size());
        
        // by subscription through manager
        assertEquals(2, mgr.getEntries(sub, 0, 10).size());
        
        // by group
        PlanetGroup group = mgr.getGroupById(testGroup1.getId());
        assertEquals(3, mgr.getEntries(group, 0, 10).size());
        
        // by group with timeframe constraint
        assertEquals(0, mgr.getEntries(group, new Date(), null, 0, 10).size());
    }
