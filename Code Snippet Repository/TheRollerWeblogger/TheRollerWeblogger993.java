    public void testCreateAnEntryWithTagsShortcut() throws Exception {
        try {
            WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            WeblogEntry entry;
            testWeblog = TestUtils.getManagedWebsite(testWeblog);
            testUser = TestUtils.getManagedUser(testUser);

            WeblogEntry testEntry = new WeblogEntry();
            testEntry.setTitle("entryTestEntry");
            testEntry.setLink("testEntryLink");
            testEntry.setText("blah blah entry");
            testEntry.setAnchor("testEntryAnchor");
            testEntry.setPubTime(
                    new java.sql.Timestamp(new java.util.Date().getTime()));
            testEntry.setUpdateTime(
                    new java.sql.Timestamp(new java.util.Date().getTime()));
            testEntry.setWebsite(testWeblog);
            testEntry.setCreatorUserName(testUser.getUserName());
            testEntry.setCategory(testWeblog.getWeblogCategory("General"));

            // shortcut
            testEntry.addTag("testTag");

            // create a weblog entry
            mgr.saveWeblogEntry(testEntry);
            String id = testEntry.getId();
            TestUtils.endSession(true);

            // make sure entry was created
            entry = mgr.getWeblogEntry(id);
            assertNotNull(entry);
            assertEquals(testEntry, entry);
            assertNotNull(entry.getTags());
            assertEquals(1, entry.getTags().size());
            assertEquals("testtag", (entry.getTags()
                    .iterator().next()).getName());
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
