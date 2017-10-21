    public void _testBookmarkImport() throws Exception {
        
        InputStream fis = this.getClass().getResourceAsStream("/bookmarks.opml");
        getRoller().getBookmarkManager().importBookmarks(
                TestUtils.getManagedWebsite(testWeblog), "ZZZ_imports_ZZZ", fileToString(fis));
        TestUtils.endSession(true);
        
        WeblogBookmarkFolder fd = null;
        
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        fd = getRoller().getBookmarkManager().getFolder(testWeblog, "ZZZ_imports_ZZZ");
        assertTrue(fd.retrieveBookmarks().size() > 0 );
        getRoller().getBookmarkManager().removeFolder(fd);
        TestUtils.endSession(true);
    }
