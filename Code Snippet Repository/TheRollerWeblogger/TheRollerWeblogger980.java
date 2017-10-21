    public void setUp() {
        
        log.info("BEGIN");
        
        try {
            // setup weblogger
            TestUtils.setupWeblogger();
            
            testUser = TestUtils.setupUser("categoryTestUser");
            testWeblog = TestUtils.setupWeblog("categoryTestWeblog", testUser);
            
            // setup several categories for testing
            cat1 = TestUtils.setupWeblogCategory(testWeblog, "catTest-cat1");
            cat2 = TestUtils.setupWeblogCategory(testWeblog, "catTest-cat2");
            cat3 = TestUtils.setupWeblogCategory(testWeblog, "catTest-cat3");
            
            // a simple test cat at the root level
            testCat = TestUtils.setupWeblogCategory(testWeblog, "catTest-testCat");
            
            TestUtils.endSession(true);
        } catch (Throwable t) {
            log.error("ERROR in setup", t);
        }
        
        log.info("END");
    }
