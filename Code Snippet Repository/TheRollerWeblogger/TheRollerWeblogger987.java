    public void testRemoveEntryTagCascading() throws Exception {

        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();

        // setup some test entries to use
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        testUser = TestUtils.getManagedUser(testUser);
        WeblogEntry entry = TestUtils.setupWeblogEntry("entry1", testWeblog, testUser);
        entry.addTag("testTag");
        String id = entry.getId();
        mgr.saveWeblogEntry(entry);
        TestUtils.endSession(true);

        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
        wesc.setWeblog(testWeblog);
        // tags are always saved lowercase (testTag -> testtag)
        wesc.setTags(Arrays.asList("testtag"));
        List results = mgr.getWeblogEntries(wesc);
        assertEquals(1, results.size());
        WeblogEntry testEntry = (WeblogEntry) results.iterator().next();
        assertEquals(entry, testEntry);

        // teardown our test entry
        TestUtils.teardownWeblogEntry(id);
        TestUtils.endSession(true);

        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        results = mgr.getWeblogEntries(wesc);
        assertEquals(0, results.size());

        // terminate
        TestUtils.endSession(true);
    } 
