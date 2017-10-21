    public void setUp() throws Exception {
        
        // setup weblogger
        TestUtils.setupWeblogger();
        
        try {
            testUser = TestUtils.setupUser("wtTestUser");
            testWeblog = TestUtils.setupWeblog("wtTestWeblog", testUser);
            TestUtils.endSession(true);
        } catch (Exception ex) {
            log.error(ex);
            throw new Exception("Test setup failed", ex);
        }
        
        testPage = new WeblogTemplate();
        testPage.setAction(ComponentType.WEBLOG);
        testPage.setName("testTemplate");
        testPage.setDescription("Test Weblog Template");
        testPage.setLink("testTemp");
        testPage.setLastModified(new java.util.Date());
        testPage.setWeblog(TestUtils.getManagedWebsite(testWeblog));
    }
