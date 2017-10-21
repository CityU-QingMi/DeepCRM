    public void setUp() throws Exception {
        
        log.info("BEGIN");
        
        // setup weblogger
        TestUtils.setupWeblogger();
        
        try {
            testUser = TestUtils.setupUser("folderFuncTestUser");
            testWeblog = TestUtils.setupWeblog("folderFuncTestWeblog", testUser);
            
            // setup a category tree to use for testing
            f1 = TestUtils.setupFolder(testWeblog, "folderFuncTest-f1");
            f2 = TestUtils.setupFolder(testWeblog, "folderFuncTest-f2");
            f3 = TestUtils.setupFolder(testWeblog, "folderFuncTest-f3");
            
            // a simple test folder at the root level
            testFolder = TestUtils.setupFolder(testWeblog, "folderFuncTest-testFolder");
            
            TestUtils.endSession(true);
        } catch (Exception ex) {
            log.error(ex);
            throw new Exception("Test setup failed", ex);
        }
        
        log.info("END");
    }
