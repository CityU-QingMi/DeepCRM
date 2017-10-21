    public void testLookupFolderById() throws Exception {
        
        log.info("BEGIN");
        
        BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
        
        // test lookup by id
        WeblogBookmarkFolder testFolder = bmgr.getFolder(f1.getId());
        assertNotNull(testFolder);
        assertEquals(f1, testFolder);
        
        log.info("END");
    }
