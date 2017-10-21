    public void testHasFolder() throws Exception {
        
        log.info("BEGIN");
        
        testWeblog = TestUtils.getManagedWebsite(testWeblog);

        // check that weblog has folder
        assertTrue(testWeblog.hasBookmarkFolder(testFolder.getName()));
        
        log.info("END");
    }
