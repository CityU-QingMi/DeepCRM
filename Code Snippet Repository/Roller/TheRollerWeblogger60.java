    public static PingTarget setupPingTarget(String name, String url)
            throws Exception {

        PingTarget testPing = new PingTarget();
        testPing.setName("testCommonPing");
        testPing.setPingUrl("http://localhost/testCommonPing");

        // store ping
        PingTargetManager pingMgr = WebloggerFactory.getWeblogger()
                .getPingTargetManager();
        pingMgr.savePingTarget(testPing);

        // flush to db
        WebloggerFactory.getWeblogger().flush();

        // query for it
        PingTarget ping = pingMgr.getPingTarget(testPing.getId());

        if (ping == null) {
            throw new WebloggerException("error setting up ping target");
        }

        return ping;
    }
