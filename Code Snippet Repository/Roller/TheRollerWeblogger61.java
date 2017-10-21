    public static void teardownPingTarget(String id) throws Exception {

        // query for it
        PingTargetManager pingMgr = WebloggerFactory.getWeblogger()
                .getPingTargetManager();
        PingTarget ping = pingMgr.getPingTarget(id);

        // remove the ping
        pingMgr.removePingTarget(ping);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }
