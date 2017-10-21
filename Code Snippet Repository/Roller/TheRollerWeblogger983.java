    public void testLookupCategoryByName() throws Exception {
        
        log.info("BEGIN");
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        WeblogCategory cat = mgr.getWeblogCategoryByName(testWeblog, "catTest-cat1");
        assertNotNull(cat);
        assertEquals(cat, cat1);
        
        cat = mgr.getWeblogCategoryByName(testWeblog, "catTest-cat3");
        assertNotNull(cat);
        assertEquals(cat, cat3);
        
        // test lazy lookup, specifying just a name without slashes
        cat = mgr.getWeblogCategoryByName(testWeblog, "catTest-cat1");
        assertNotNull(cat);
        assertEquals(cat, cat1);

        log.info("END");
    }
