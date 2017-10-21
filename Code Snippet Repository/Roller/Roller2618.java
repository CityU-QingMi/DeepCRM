    @Test
    public void testCreateMediaFileDirectory() throws Exception {
        User testUser = null;
        Weblog testWeblog = null;

        // TODO: Setup code, to be moved to setUp method.
        log.info("Before setting up weblogger");
        // setup weblogger
        try {
            testUser = TestUtils.setupUser("mediaFileTestUser");
            testWeblog = TestUtils.setupWeblog("mediaFileTestWeblog", testUser);
            TestUtils.endSession(true);
        } catch (Exception ex) {
            log.error(ex);
            throw new Exception("Test setup failed", ex);
        }

/**/
/**/
/**/
        MediaFileManager mfMgr = WebloggerFactory.getWeblogger()
                .getMediaFileManager();

        testWeblog = TestUtils.getManagedWebsite(testWeblog);

        // no need to create root directory, that is done automatically now
        MediaFileDirectory directory = mfMgr
                .getDefaultMediaFileDirectory(testWeblog);

        TestUtils.endSession(true);

        MediaFileDirectory directoryById = mfMgr
                .getMediaFileDirectory(directory.getId());
        assertEquals(directory, directoryById);

        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        MediaFileDirectory rootDirectory = mfMgr
                .getDefaultMediaFileDirectory(testWeblog);
        assertEquals(directory, rootDirectory);

        TestUtils.endSession(true);
        TestUtils.teardownWeblog(testWeblog.getId());
        TestUtils.teardownUser(testUser.getUserName());
        TestUtils.endSession(true);
    }
