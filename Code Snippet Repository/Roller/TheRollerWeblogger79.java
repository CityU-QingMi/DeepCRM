    public static void teardownWeblog(String id) throws Exception {

        // lookup the weblog
        WeblogManager mgr = WebloggerFactory.getWeblogger().getWeblogManager();
        Weblog weblog = mgr.getWeblog(id);

        // remove the weblog
        mgr.removeWeblog(weblog);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }
