    public void testTagsExist() throws Exception {
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();

        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        testUser = TestUtils.getManagedUser(testUser);
        Weblog weblog = TestUtils.setupWeblog("tagsExistWeblog1", testUser);
        String wid = weblog.getId();
        
        // setup some test entries to use
        WeblogEntry entry = TestUtils.setupWeblogEntry("tagsExistEntry1", testWeblog, testUser);
        String id1 = entry.getId();
        entry.addTag("blahTag");
        entry.addTag("fooTag");
        mgr.saveWeblogEntry(entry);

        WeblogEntry entry2 = TestUtils.setupWeblogEntry("tagsExistEntry2", weblog, testUser);
        String id2 = entry2.getId();
        entry2.addTag("aaaTag");
        entry2.addTag("bbbTag");
        mgr.saveWeblogEntry(entry2);
        TestUtils.endSession(true);
        
        // we'll need these
        List<String> tags1 = new ArrayList<String>();
        tags1.add("nonExistTag");
        
        List<String> tags2 = new ArrayList<String>();
        tags2.add("blahtag");
        
        // test site-wide
        assertTrue(mgr.getTagComboExists(tags2, null));
        assertFalse(mgr.getTagComboExists(tags1, null));
        
        // test weblog specific
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        weblog = TestUtils.getManagedWebsite(weblog);
        assertTrue(mgr.getTagComboExists(tags2, testWeblog));
        assertFalse(mgr.getTagComboExists(tags1, testWeblog));
        assertFalse(mgr.getTagComboExists(tags2, weblog));
        
        // teardown our test data
        TestUtils.teardownWeblogEntry(id1);
        TestUtils.teardownWeblogEntry(id2);
        TestUtils.endSession(true);

        TestUtils.teardownWeblog(wid);
        TestUtils.endSession(true);
    }
