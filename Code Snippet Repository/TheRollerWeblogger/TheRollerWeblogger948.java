    public void testLookupAllFoldersByWeblog() throws Exception {
        
        log.info("BEGIN");
        
        BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
        
        // get all folders, including root
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        List allFolders = bmgr.getAllFolders(testWeblog);
        assertNotNull(allFolders);
        assertEquals(5, allFolders.size());
        
        log.info("END");
    }
