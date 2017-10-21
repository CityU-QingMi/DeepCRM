    public void testAddMultipleIdenticalTags() throws Exception {

        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();

        // setup some test entries to use
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        testUser = TestUtils.getManagedUser(testUser);
        WeblogEntry entry = TestUtils.setupWeblogEntry("entry1", testWeblog, testUser);
        entry.addTag("testTag");
        String id = entry.getId();
        mgr.saveWeblogEntry(entry);
        TestUtils.endSession(true);

        entry = mgr.getWeblogEntry(id);
        entry.addTag("testTag");
        mgr.saveWeblogEntry(entry);
        TestUtils.endSession(true);

        entry = mgr.getWeblogEntry(id);
        assertEquals(1, entry.getTags().size());

        // teardown our test entry
        TestUtils.teardownWeblogEntry(id);
        TestUtils.endSession(true);
    }    
