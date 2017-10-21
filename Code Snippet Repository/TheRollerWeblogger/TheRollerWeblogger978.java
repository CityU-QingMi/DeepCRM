    public void testWeblogCategoryEquality() throws Exception {
        
        log.info("BEGIN");
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        testWeblog = TestUtils.getManagedWebsite(testWeblog);

        WeblogCategory testCat = new WeblogCategory(testWeblog, "root", "desc", null);
        WeblogCategory testCat2 = new WeblogCategory(testWeblog, "root2", "desc2", null);
        assertFalse(testCat2.equals(testCat));
        mgr.removeWeblogCategory(testCat);
        mgr.removeWeblogCategory(testCat2);

        log.info("END");
    }
