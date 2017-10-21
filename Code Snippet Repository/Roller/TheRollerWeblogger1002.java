    protected void setUp() throws Exception {
        // create weblog with three entries and two comments per entry
        user1 = TestUtils.setupUser("a_commentCountTestUser");
        user2 = TestUtils.setupUser("b_commentCountTestUser");
        
        website1 = TestUtils.setupWeblog("a_testWebsite1", user1);
        entry11 = TestUtils.setupWeblogEntry(
                "anchor11", website1, user1);
        comment11 = TestUtils.setupComment("Comment11", entry11);
        comment12 = TestUtils.setupComment("Comment12", entry11);
        entry12 = TestUtils.setupWeblogEntry(
                "anchor12", website1, user1);
        comment13 = TestUtils.setupComment("Comment13", entry12);
        
        website2 = TestUtils.setupWeblog("b_testWebsite2", user1);
        entry21 = TestUtils.setupWeblogEntry(
                "anchor21", website2, user1);
        comment21 = TestUtils.setupComment("Comment21", entry21);
        TestUtils.endSession(true);

        Thread.sleep(RollerConstants.SEC_IN_MS);
    }
