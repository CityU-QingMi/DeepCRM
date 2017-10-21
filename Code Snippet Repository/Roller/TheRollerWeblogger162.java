    OAuthConsumer consumerFromRecord(OAuthConsumerRecord record) {
        OAuthConsumer consumer = null;
        if (record != null) {
            consumer = new OAuthConsumer(
                null,
                record.getConsumerKey(),
                record.getConsumerSecret(),
                getServiceProvider());
            if (record.getUserName() != null) {
                consumer.setProperty("userId", record.getUserName());
            }
        }
        return consumer;
    }
