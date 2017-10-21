    public static void teardownEntry(String id) throws Exception {

        // lookup
        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();
        SubscriptionEntry entry = mgr.getEntryById(id);

        // remove
        mgr.deleteEntry(entry);
        entry.getSubscription().getEntries().remove(entry);

        // flush
        WebloggerFactory.getWeblogger().flush();
    }
