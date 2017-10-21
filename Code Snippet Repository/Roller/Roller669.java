    public OAuthConsumer getConsumer() throws WebloggerException {
        OAuthConsumerRecord record = null;
        try {
            TypedQuery<OAuthConsumerRecord> q = strategy.getNamedQuery("OAuthConsumerRecord.getSiteWideConsumer",
                    OAuthConsumerRecord.class);
            record = q.getSingleResult();

        } catch (Exception ex) {
            log.debug("ERROR fetching site-wide consumer", ex);
        }
        if (record != null) {
            return new OAuthConsumer(
                null,
                record.getConsumerKey(),
                record.getConsumerSecret(),
                getServiceProvider());
        }
        return null;
    }
