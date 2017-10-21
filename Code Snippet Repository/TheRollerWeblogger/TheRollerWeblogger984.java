    public void testLookupAllCategoriesByWeblog() throws Exception {
        
        log.info("BEGIN");
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        List cats = mgr.getWeblogCategories(testWeblog);
        assertNotNull(cats);
        assertEquals(5, cats.size());
        
        log.info("END");
    }
