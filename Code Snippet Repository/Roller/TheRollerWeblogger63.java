    public static void teardownAutoPing(String id) throws Exception {

        // query for it
        AutoPingManager mgr = WebloggerFactory.getWeblogger()
                .getAutopingManager();
        AutoPing autoPing = mgr.getAutoPing(id);

        // remove the auto ping
        mgr.removeAutoPing(autoPing);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }
