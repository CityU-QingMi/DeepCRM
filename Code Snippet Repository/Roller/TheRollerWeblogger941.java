    public void testBasicCRUD() throws Exception {
        
        log.info("BEGIN");
        
        BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
        
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        WeblogBookmarkFolder root = bmgr.getDefaultFolder(testWeblog);
        
        // start out with just default folder and no bookmarks
        assertEquals(1, testWeblog.getBookmarkFolders().size());
        assertEquals(0, root.getBookmarks().size());
        
        // add a folder
        WeblogBookmarkFolder newFolder = new WeblogBookmarkFolder("folderBasicCRUD", TestUtils.getManagedWebsite(testWeblog));
        bmgr.saveFolder(newFolder);
        TestUtils.endSession(true);
        
        // check that folder was saved
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        assertEquals(2, testWeblog.getBookmarkFolders().size());
        WeblogBookmarkFolder folder = testWeblog.getBookmarkFolders().get(1);
        assertEquals(newFolder, folder);
        
        // modify folder
        folder.setName("folderTest1");
        bmgr.saveFolder(folder);
        TestUtils.endSession(true);
        
        // check that folder was saved
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        folder = testWeblog.getBookmarkFolders().get(1);
        assertEquals("folderTest1", folder.getName());
        
        // test remove folder
        bmgr.removeFolder(folder);
        TestUtils.endSession(true);
        
        // make sure folder was removed
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        assertEquals(1, testWeblog.getBookmarkFolders().size());
        folder = bmgr.getFolder(newFolder.getId());
        assertNull(folder);
        
        log.info("END");
    }
