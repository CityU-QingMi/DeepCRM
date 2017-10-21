    OAuthConsumer getConsumerByKey(String consumerKey) {
        OAuthConsumerRecord record = null;
        try {
            TypedQuery<OAuthConsumerRecord> q = strategy.getNamedQuery("OAuthConsumerRecord.getByConsumerKey",
                    OAuthConsumerRecord.class);
            q.setParameter(1, consumerKey);
            record = q.getSingleResult();

        } catch (Exception ex) {
            log.debug("ERROR fetching consumer", ex);
        }
        return consumerFromRecord(record);
    }
