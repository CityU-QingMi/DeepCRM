    public List<SubscriptionEntry> getFeedEntries(String feedUrl, int maxEntries) throws Exception {
        try {
            Subscription sub = planetManager.getSubscription(feedUrl);
            if(sub != null) {
                return planetManager.getEntries(sub, 0, maxEntries);
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
