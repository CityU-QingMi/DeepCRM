    public OAuthConsumer addConsumer(String username, String consumerKey) throws OAuthException {

        OAuthConsumerRecord record = new OAuthConsumerRecord();
        record.setConsumerKey(consumerKey);
        record.setUserName(username);
        record.setConsumerSecret(UUID.randomUUID().toString());

        try {
            strategy.store(record);
        } catch (WebloggerException ex) {
            throw new OAuthException("ERROR storing accessor", ex);
        }
        
        return new OAuthConsumer(null,
            record.getConsumerKey(),
            record.getConsumerSecret(),
            getServiceProvider());
    }
