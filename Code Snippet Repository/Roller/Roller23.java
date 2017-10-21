    protected void setUp() throws Exception {
        // setup weblogger
        TestUtils.setupWeblogger();
        
        mgr = new CommentValidationManager();
        
        user = TestUtils.setupUser("johndoe");
        //TestUtils.endSession(true);

        weblog = TestUtils.setupWeblog("doeblog", user);
        //TestUtils.endSession(true)
        
        entry = TestUtils.setupWeblogEntry("anchor1", weblog, user);

        TestUtils.endSession(true);
    }
