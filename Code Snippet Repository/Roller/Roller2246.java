    public static AutoPing setupAutoPing(PingTarget ping, Weblog weblog)
            throws Exception {

        AutoPingManager mgr = WebloggerFactory.getWeblogger()
                .getAutopingManager();

        // store auto ping
        AutoPing autoPing = new AutoPing(null, ping, getManagedWebsite(weblog));
        mgr.saveAutoPing(autoPing);

        // flush to db
        WebloggerFactory.getWeblogger().flush();

        // query for it
        autoPing = mgr.getAutoPing(autoPing.getId());

        if (autoPing == null) {
            throw new WebloggerException("error setting up auto ping");
        }

        return autoPing;
    }
