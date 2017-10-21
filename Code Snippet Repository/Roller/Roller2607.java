    public void testUniquenessOfFolderNames() throws Exception {
        
        log.info("BEGIN");
        try {
            BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
            testWeblog = TestUtils.getManagedWebsite(testWeblog);

            boolean exception = false;
            try {
                // child folder with same name as first
                WeblogBookmarkFolder dupeFolder = new WeblogBookmarkFolder(testFolder.getName(), testWeblog);
                bmgr.saveFolder(dupeFolder);
                TestUtils.endSession(true);
            } catch (Throwable e) {
                exception = true;
            }

            assertTrue(exception);
            
        } catch (Throwable t) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw); 
            t.printStackTrace(pw);
            log.info(sw.toString());
        }        
        TestUtils.endSession(true);

        log.info("END");
    }
