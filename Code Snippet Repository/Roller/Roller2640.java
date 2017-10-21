    public void testUserLookups() throws Exception {
        
        UserManager mgr = WebloggerFactory.getWeblogger().getUserManager();
        User user = null;
        
        // add test user
        User testUser = TestUtils.setupUser("userTestUser");
        TestUtils.endSession(true);
        
        // lookup by username
        user = mgr.getUserByUserName(testUser.getUserName());
        assertNotNull(user);
        assertEquals(testUser.getUserName(), user.getUserName());
        
        // lookup by id
        String userName = user.getUserName();
        user = null;
        user = mgr.getUserByUserName(userName);
        assertNotNull(user);
        assertEquals(testUser.getUserName(), user.getUserName());
        
        // lookup by UserName (part)
        user = null;
        List users1 = mgr.getUsersStartingWith(testUser.getUserName().substring(0, 3), Boolean.TRUE, 0, 1);
        assertEquals(1, users1.size());
        user = (User) users1.get(0);
        assertNotNull(user);
        assertEquals(testUser.getUserName(), user.getUserName());
        
        // lookup by Email (part)
        user = null;
        List users2 = mgr.getUsersStartingWith(testUser.getEmailAddress().substring(0, 3), Boolean.TRUE, 0, 1);
        assertEquals(1, users2.size());
        user = (User) users2.get(0);
        assertNotNull(user);
        assertEquals(testUser.getUserName(), user.getUserName());
        
        // make sure disable users are not returned
        user.setEnabled(Boolean.FALSE);
        mgr.saveUser(user);
        TestUtils.endSession(true);
        user = null;
        user = mgr.getUserByUserName(testUser.getUserName());
        assertNull(user);
        
        // remove test user
        TestUtils.teardownUser(testUser.getUserName());
        TestUtils.endSession(true);
    }
