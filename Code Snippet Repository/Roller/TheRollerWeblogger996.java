    public void testRemoveTagsViaShortcut() throws Exception {
        try {
            WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();

            // setup some test entries to use
            testWeblog = TestUtils.getManagedWebsite(testWeblog);
            testUser = TestUtils.getManagedUser(testUser);
            WeblogEntry entry = TestUtils.setupWeblogEntry("entry1", testWeblog, testUser);
            entry.addTag("testTag");
            entry.addTag("testTag2");
            String id = entry.getId();
            mgr.saveWeblogEntry(entry);
            TestUtils.endSession(true);

            entry = mgr.getWeblogEntry(id);
            assertEquals(2, entry.getTags().size());
            TestUtils.endSession(true);

            entry = mgr.getWeblogEntry(id);
            entry.setTagsAsString("");
            mgr.saveWeblogEntry(entry);
            TestUtils.endSession(true);

            entry = mgr.getWeblogEntry(id);
            assertEquals(0, entry.getTags().size());
            TestUtils.endSession(true);

            // teardown our test entry
            TestUtils.teardownWeblogEntry(id);
            TestUtils.endSession(true);
            
        } catch (Throwable t) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw); 
            t.printStackTrace(pw);
            log.info(sw.toString());
        }
    }
