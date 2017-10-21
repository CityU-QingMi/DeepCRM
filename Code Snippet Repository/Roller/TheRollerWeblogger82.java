    public static void teardownWeblogCategory(String id) throws Exception {

        // lookup the cat
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger()
                .getWeblogEntryManager();
        WeblogCategory cat = mgr.getWeblogCategory(id);

        // remove the cat
        mgr.removeWeblogCategory(cat);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }
