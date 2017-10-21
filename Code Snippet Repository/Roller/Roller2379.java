    public static void teardownSubscription(String id) throws Exception {

        // lookup
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        Subscription sub = mgr.getSubscriptionById(id);

        // remove
        mgr.deleteSubscription(sub);

        // flush
        WebloggerFactory.getWeblogger().flush();
    }
