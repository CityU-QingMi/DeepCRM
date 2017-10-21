    public static SubscriptionEntry setupEntry(Subscription sub, String title)
            throws Exception {

        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();

        // make sure we are using a persistent object
        Subscription testSub = mgr.getSubscriptionById(sub.getId());

        // store
        SubscriptionEntry testEntry = new SubscriptionEntry();
        testEntry.setPermalink(title);
        testEntry.setTitle(title);
        testEntry
                .setPubTime(new java.sql.Timestamp(System.currentTimeMillis()));
        testEntry.setSubscription(testSub);
        testSub.getEntries().add(testEntry);
        mgr.saveEntry(testEntry);

        // flush
        WebloggerFactory.getWeblogger().flush();

        // query to make sure we return the persisted object
        SubscriptionEntry entry = mgr.getEntryById(testEntry.getId());

        if (entry == null) {
            throw new WebloggerException("error inserting new entry");
        }

        return entry;
    }
