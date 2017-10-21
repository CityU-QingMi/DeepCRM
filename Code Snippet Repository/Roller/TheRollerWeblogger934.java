    public void setUp() throws Exception {
        
        // setup weblogger
        TestUtils.setupWeblogger();
        
        try {
            testUser = TestUtils.setupUser("commentTestUser");
            testWeblog = TestUtils.setupWeblog("commentTestWeblog", testUser);
            testEntry = TestUtils.setupWeblogEntry("commentTestEntry", testWeblog, testUser);
            TestUtils.endSession(true);
        } catch (Exception ex) {
            log.error(ex);
            throw new Exception("Test setup failed", ex);
        }
    }
