    public void tearDown() throws Exception {
        
        try {
            TestUtils.teardownWeblogEntry(testEntry.getId());
            TestUtils.teardownWeblog(testWeblog.getId());
            TestUtils.teardownUser(testUser.getUserName());
            TestUtils.endSession(true);
        } catch (Exception ex) {
            log.error(ex);
            throw new Exception("Test teardown failed", ex);
        }
    }
