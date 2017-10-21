    public static WeblogHitCount setupHitCount(Weblog weblog, int amount)
            throws Exception {

        WeblogEntryManager mgr = WebloggerFactory.getWeblogger()
                .getWeblogEntryManager();

        // store
        WeblogHitCount testCount = new WeblogHitCount();
        testCount.setWeblog(getManagedWebsite(weblog));
        testCount.setDailyHits(amount);
        mgr.saveHitCount(testCount);

        // flush to db
        WebloggerFactory.getWeblogger().flush();

        // query for it
        testCount = mgr.getHitCount(testCount.getId());

        if (testCount == null) {
            throw new WebloggerException("error setting up hit count");
        }

        return testCount;
    }
