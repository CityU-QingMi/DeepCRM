    protected void tearDown() throws Exception {
        
        // TODO: ATLAS figure out why comments must be torn down first
        TestUtils.teardownComment(comment11.getId());
        TestUtils.teardownComment(comment12.getId());
        TestUtils.teardownComment(comment13.getId());
        TestUtils.teardownWeblog(website1.getId());
        
        TestUtils.teardownComment(comment21.getId());
        TestUtils.teardownWeblog(website2.getId());  
        
        TestUtils.teardownUser(user1.getUserName());        
        TestUtils.teardownUser(user2.getUserName());        
        
        TestUtils.endSession(true);
    }
