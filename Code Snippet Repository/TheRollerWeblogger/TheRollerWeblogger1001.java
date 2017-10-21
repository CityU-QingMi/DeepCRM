    public void testPermissionsLookups() throws Exception {
        
        WeblogManager mgr = WebloggerFactory.getWeblogger().getWeblogManager();
        WeblogTemplate page;
        
        // create page
        mgr.saveTemplate(testPage);
        String id = testPage.getId();
        TestUtils.endSession(true);
        
        // lookup by id
        page = mgr.getTemplate(id);
        assertNotNull(page);

        // lookup by action
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        page = mgr.getTemplateByAction(testWeblog, testPage.getAction());
        assertNotNull(page);

        // lookup by name
        page = mgr.getTemplateByName(testWeblog, testPage.getName());
        assertNotNull(page);

        // lookup by link
        page = mgr.getTemplateByLink(testWeblog, testPage.getLink());
        assertNotNull(page);

        // lookup all pages for weblog
        List pages = mgr.getTemplates(testWeblog);
        assertNotNull(pages);
        assertEquals(1, pages.size());
        
        // delete page
        mgr.removeTemplate(page);
        TestUtils.endSession(true);
    }
