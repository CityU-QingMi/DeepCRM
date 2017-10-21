    public static Weblog setupWeblog(String handle, User creator)
            throws Exception {

        Weblog testWeblog = new Weblog();
        testWeblog.setName("Test Weblog");
        testWeblog.setTagline("Test Weblog");
        testWeblog.setHandle(handle);
        testWeblog.setEmailAddress("testweblog@dev.null");
        testWeblog.setEditorPage("editor-text.jsp");
        testWeblog.setBlacklist("");
        testWeblog.setEditorTheme("basic");
        testWeblog.setLocale("en_US");
        testWeblog.setTimeZone("America/Los_Angeles");
        testWeblog.setDateCreated(new java.util.Date());
        testWeblog.setCreatorUserName(creator.getUserName());

        // add weblog
        WeblogManager mgr = WebloggerFactory.getWeblogger().getWeblogManager();
        mgr.addWeblog(testWeblog);

        // flush to db
        WebloggerFactory.getWeblogger().flush();

        // query for the new weblog and return it
        Weblog weblog = mgr.getWeblogByHandle(handle);

        if (weblog == null) {
            throw new WebloggerException("error setting up weblog");
        }

        return weblog;
    }
