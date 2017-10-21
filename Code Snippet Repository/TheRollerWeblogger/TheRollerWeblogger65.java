    public static void teardownHitCount(String id) throws Exception {

        // query for it
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger()
                .getWeblogEntryManager();
        WeblogHitCount testCount = mgr.getHitCount(id);

        // remove
        mgr.removeHitCount(testCount);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }
