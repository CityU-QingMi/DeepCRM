     public void testUpdateTagTime() throws Exception {
         WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();

        // setup some test entries to use
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        testUser = TestUtils.getManagedUser(testUser);
        WeblogEntry entry = TestUtils.setupWeblogEntry("entry1", testWeblog, testUser);
        String id = entry.getId();

        entry.addTag("testWillStayTag");
        entry.addTag("testTagWillBeRemoved");
        mgr.saveWeblogEntry(entry);
        TestUtils.endSession(true);

        entry = mgr.getWeblogEntry(id);
        assertEquals(2, entry.getTags().size());

        Timestamp original = null;

        for (WeblogEntryTag tagData : entry.getTags()) {
            if (tagData.getName().equals("testwillstaytag"))
                original = tagData.getTime();
        }

        entry.setTagsAsString("testwillstaytag testnewtag testnewtag3");
        mgr.saveWeblogEntry(entry);
        TestUtils.endSession(true);

        entry = mgr.getWeblogEntry(id);
        HashSet<String> tagNames = new HashSet<String>();
        for (WeblogEntryTag tagData : entry.getTags()) {
            tagNames.add(tagData.getName());
            if (tagData.getName().equals("testwillstaytag")) {
                assertEquals(original, tagData.getTime());
            }
        }

        assertEquals(3, entry.getTags().size());
        assertEquals(3, tagNames.size());
        assertEquals(true, tagNames.contains("testwillstaytag"));
        assertEquals(true, tagNames.contains("testnewtag"));
        assertEquals(true, tagNames.contains("testnewtag3"));

        // teardown our test entry
        TestUtils.teardownWeblogEntry(id);
        TestUtils.endSession(true);
    }
