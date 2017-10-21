    public void testPermissionChecks() throws Exception {
        
        log.info("BEGIN");
       
        WeblogPermission perm = 
            new WeblogPermission(testWeblog, testUser, WeblogPermission.POST);
        UserManager umgr = WebloggerFactory.getWeblogger().getUserManager();
        assertTrue(umgr.checkPermission(perm, testUser));
        
        // we need a second user for this test
        User adminUser = TestUtils.setupUser("adminUser");
        umgr.grantRole("admin", adminUser);
        TestUtils.endSession(true);

        // because adminUser is a global admin, they should have POST perm
        WeblogPermission perm2 = 
            new WeblogPermission(testWeblog, testUser, WeblogPermission.POST);
        assertTrue(umgr.checkPermission(perm, testUser));

        // cleanup the extra test user
        TestUtils.teardownUser(adminUser.getUserName());
        TestUtils.endSession(true);
        log.info("END");
    }
