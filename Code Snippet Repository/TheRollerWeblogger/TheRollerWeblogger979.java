    public void testCategoryCascadingDelete() throws Exception {
        
        log.info("BEGIN");
        
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
        
        // root category is always available
        testWeblog = TestUtils.getManagedWebsite(testWeblog);

        // add a category above default one
        WeblogCategory testCat = new WeblogCategory(testWeblog, "SampleCategory", null, null);
        mgr.saveWeblogCategory(testCat);
        TestUtils.endSession(true);
        
        // check that testCat can be retrieved
        testWeblog = TestUtils.getManagedWebsite(testWeblog);

        assertEquals(2, testWeblog.getWeblogCategories().size());
        testCat = testWeblog.getWeblogCategories().get(1);
        assertEquals("SampleCategory", testCat.getName());

        // now delete category and subcats should be deleted by cascade
        mgr.removeWeblogCategory(testCat);
        TestUtils.endSession(true);
        
        // verify cascading delete succeeded
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        assertEquals(1, testWeblog.getWeblogCategories().size());
        assertNull(mgr.getWeblogCategoryByName(TestUtils.getManagedWebsite(testWeblog), "SampleCategory"));
        
        log.info("END");
    }
