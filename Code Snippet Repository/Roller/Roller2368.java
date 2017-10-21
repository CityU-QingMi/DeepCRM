    public static Subscription setupSubscription(String feedUrl)
            throws Exception {

        PlanetManager mgr = WebloggerFactory.getWeblogger().getPlanetManager();

        // store
        Subscription testSub = new Subscription();
        testSub.setFeedURL(feedUrl);
        testSub.setTitle(feedUrl);
        mgr.saveSubscription(testSub);

        // flush
        WebloggerFactory.getWeblogger().flush();

        // query to make sure we return the persisted object
        Subscription sub = mgr.getSubscriptionById(testSub.getId());

        if (sub == null) {
            throw new WebloggerException("error inserting new subscription");
        }

        return sub;
    }
