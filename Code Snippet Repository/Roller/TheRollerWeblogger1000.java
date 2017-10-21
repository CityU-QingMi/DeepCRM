    public void testTemplateCRUD() throws Exception {
        
        WeblogManager mgr = WebloggerFactory.getWeblogger().getWeblogManager();
        WeblogTemplate template;
        
        // create template
        mgr.saveTemplate(testPage);
        TestUtils.endSession(true);
        
        // check that create was successful
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        template = mgr.getTemplateByName(testWeblog, testPage.getName());
        assertNotNull(template);

        // update template
        template.setName("testtesttest");
        mgr.saveTemplate(template);
        TestUtils.endSession(true);
        
        // check that update was successful
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        template = mgr.getTemplateByName(testWeblog, "testtesttest");
        assertNotNull(template);

        // delete template
        mgr.removeTemplate(template);
        TestUtils.endSession(true);
        
        // check that delete was successful
        testWeblog = TestUtils.getManagedWebsite(testWeblog);
        template = mgr.getTemplateByName(testWeblog, testPage.getName());
        assertNull(template);
    }
