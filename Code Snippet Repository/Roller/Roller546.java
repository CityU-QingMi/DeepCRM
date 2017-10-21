    public OAuthConsumer getConsumer(
            OAuthMessage requestMessage)
            throws IOException, OAuthProblemException {

        OAuthConsumer consumer;
        // try to load from local cache if not throw exception
        String consumer_key = requestMessage.getConsumerKey();

        consumer = getConsumerByKey(consumer_key);

        if(consumer == null) {
            throw new OAuthProblemException("token_rejected");
        }

        return consumer;
    }
