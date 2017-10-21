    @Override
    public void setUp() throws Exception {
        
        // setup weblogger
        TestUtils.setupWeblogger();
        
        try {
            //testUser = TestUtils.setupUser("webloggerFetcherTestUser");
            //testWeblog = TestUtils.setupWeblog("webloggerFetcherTestWeblog", testUser);
            //TestUtils.endSession(true);
        } catch (Exception ex) {
            log.error(ex);
            throw new Exception("Test setup failed", ex);
        }
    }
