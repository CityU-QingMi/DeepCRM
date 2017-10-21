    public void testGetEntriesByTag() throws Exception {
        try {
            WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();

            // setup some test entries to use
            testWeblog = TestUtils.getManagedWebsite(testWeblog);
            testUser = TestUtils.getManagedUser(testUser);
            WeblogEntry entry = TestUtils.setupWeblogEntry("entry1", testWeblog, testUser);
            String id = entry.getId();
            entry.addTag("testTag");
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
            
        } catch (Throwable t) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw); 
            t.printStackTrace(pw);
            log.info(sw.toString());
        }
    }
