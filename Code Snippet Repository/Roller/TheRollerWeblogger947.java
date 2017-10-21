    public void testLookupFolderByName() throws Exception {
        
        log.info("BEGIN");
        
        BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
        
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        WeblogBookmarkFolder folder = bmgr.getFolder(testWeblog, "folderFuncTest-f1");
        assertNotNull(folder);
        assertEquals(f1, folder);
        
        folder = bmgr.getFolder(testWeblog, "folderFuncTest-f3");
        assertNotNull(folder);
        assertEquals(f3, folder);
        
        // test to check that default folder is accessible
        folder = bmgr.getDefaultFolder(testWeblog);
        assertNotNull(folder);
        assertEquals("default", folder.getName());
        
        log.info("END");
    }
