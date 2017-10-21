    public OAuthConsumer getConsumerByUsername(String username) throws WebloggerException {
        OAuthConsumerRecord record = null;
        try {
            TypedQuery<OAuthConsumerRecord> q = strategy.getNamedQuery("OAuthConsumerRecord.getByUsername",
                    OAuthConsumerRecord.class);
            q.setParameter(1, username);
            record = q.getSingleResult();

        } catch (Exception ex) {
            log.debug("ERROR fetching consumer", ex);
        }
        if (record != null) {
            OAuthConsumer consumer = new OAuthConsumer(
                null,
                record.getConsumerKey(),
                record.getConsumerSecret(),
                getServiceProvider());
            consumer.setProperty("userName", record.getUserName());
            return consumer;
        }
        return null;
    }
